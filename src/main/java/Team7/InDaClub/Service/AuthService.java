package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.Dto.LoginDto;
import Team7.InDaClub.Domain.Dto.TokenResponse;
import Team7.InDaClub.Domain.Entity.Auth;
import Team7.InDaClub.Domain.Entity.User;
import Team7.InDaClub.Exception.ErrorCode;
import Team7.InDaClub.Exception.UserIDDuplicateException;
import Team7.InDaClub.Repository.AuthRepository;
import Team7.InDaClub.Repository.UserRepository;
import Team7.InDaClub.Security.EncryptPw;
import Team7.InDaClub.Security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final EncryptPw encryptPw;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /** 회원가입 함수 */
    @Transactional
    public User join(User _user) {
        Optional<User> duplicatedUser = userRepository.findByUserId(_user.getUserId());
        if (duplicatedUser.isPresent()) throw new UserIDDuplicateException("User ID is Duplicated", ErrorCode.USERID_DUPLICATION);
        else {
            String password = _user.getPassword();
            String salt = encryptPw.genSalt();
            _user.setSalt(salt);
            _user.setUserPw(encryptPw.encodePassword(salt, password));
            _user.setRoles(Collections.singletonList("ROLE_USER"));
        }

        return userRepository.save(_user);
    }

    /** 서버와 통신해 로그인을 하는 함수 */
    @Transactional
    public TokenResponse doLogin(LoginDto _request) throws Exception {
        User user = userRepository.findByUserId(_request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(_request.getUserPw(), user.getPassword())) {
            // 로그를 찍을 자리
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtProvider.createAccessToken(user.getUserId());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        Optional<Auth> alUser = authRepository.findByUserId(user.getId());

        if (!alUser.isPresent()) {
            Auth auth = new Auth();
            auth.setUserId(user.getId());
            auth.setRefreshToken(refreshToken);
            authRepository.save(auth);
        } else {
            alUser.ifPresent(selectUser -> {
               selectUser.setRefreshToken(refreshToken);
               authRepository.save(selectUser);
            });
        }

        TokenResponse token = new TokenResponse();
        token.setACCESS_TOKEN(accessToken);
        token.setREFRESH_TOKEN(refreshToken);
        return token;
    }

    /** access token 에 문제가 있으면 refresh token 을 통해 access token 을 재발급
     * refresh token 이 null 일경우 -> 재로그인
     * refresh token 이 유효할 경우 -> issuedAccessToken 을 통해 재발급
     * */
    @Transactional
    public TokenResponse issuedAccessToken(HttpServletRequest _request) {
        String accessToken = jwtProvider.resolveAccessToken(_request);
        String refreshToken = jwtProvider.resolveRefreshToken(_request);

        if (jwtProvider.isValidAccessToken(refreshToken)) {
            // 로그를 찍을 자리
            Claims claims = jwtProvider.getClaimsFormRefreshToken(refreshToken);
            Long id = Long.valueOf(claims.getId().toString());
            Optional<User> user = userRepository.findById(id);
            String tokenFromDB = authRepository.findByUserId(user.get().getId()).get().getRefreshToken();

            if (refreshToken.equals(tokenFromDB)) {
                accessToken = jwtProvider.createAccessToken(user.get().getUserId());
                // 로그를 찍을 자리
            } else {
                // 로그를 찍을 자리
                accessToken = null;
                refreshToken = null;
            }
        }
        TokenResponse token = new TokenResponse();
        token.setACCESS_TOKEN(accessToken);
        token.setREFRESH_TOKEN(refreshToken);
        return token;
    }

    /** access token 에서 user 를 찾는 함수 */
    public User fineUser(HttpServletRequest _request) throws Exception {
        String accessToken = jwtProvider.resolveAccessToken(_request);

        if (jwtProvider.isValidAccessToken(accessToken)) {
            Claims claims = jwtProvider.getClaimsFormAccessToken(accessToken);
            String userId = (String) claims.get("userId");
            User user = userRepository.findByUserId(userId)
                    .orElseThrow(() -> {
                        // 로그를 찍을 자리
                        return new UsernameNotFoundException("존재하지 않는 사용자입니다.");
                    });
            // 로그를 찍을 자리
            return user;
        } else {
            throw new Exception("토큰이 만료되었습니다.");
            // 로그를 찍을 자리
        }

    }
}
