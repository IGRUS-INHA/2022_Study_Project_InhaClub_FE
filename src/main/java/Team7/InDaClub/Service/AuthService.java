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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

/** 회원 가입, 로그인 관련 서비스 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final EncryptPw encryptPw;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /** 회원가입  */
    @Transactional
    public User join(User _user) {
        Optional<User> duplicatedUser = userRepository.findByUserId(_user.getUserId()); // 중복유저를 찾아 duplicatedUser 에 넣는다. 중복이 없으면 Optional 이므로 값이 없을 것이다.
        if (duplicatedUser.isPresent()) throw new UserIDDuplicateException("User ID is Duplicated", ErrorCode.USERID_DUPLICATION); // duplicatedUser 에 값이 있으면 예외를 처리한다.
        else { // duplicatedUser 에 값이 없으면 == 중복유저가 없으면 회원가입을 수행한다.
            String password = _user.getPassword();
            String salt = encryptPw.genSalt(); // EncryptPw 에서 salt 값을 만들어온다.
            _user.setSalt(salt); // 매개변수로 받아온 user 에 salt 값을 지정해준다.
            _user.setUserPw(encryptPw.encodePassword(salt, password)); // user 의 userPw 에는 == DB에 저장될 값에는 salt 로 pw 를 encode 시켜 저장한다.
            _user.setRoles(Collections.singletonList("ROLE_USER")); // user 의 권한 정의
        }

        log.info("ID: "+ _user.getUserId() + " is been saved.");
        return userRepository.save(_user); // DB에 User를 저장한다.
    }

    /** 서버와 통신해 로그인을 하는 함수 */
    @Transactional
    public TokenResponse doLogin(LoginDto _request) throws Exception {
        User user = userRepository.findByUserId(_request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")); // _request 에서 받아온 user ID 로 중복 검사를 해 중복이면 예외처리를 해준다.

        if (!passwordEncoder.matches(_request.getUserPw(), user.getPassword())) { // 만약 passwordEncoder 에서 _request 에서 받아온 pw와 DB 에서 받아온 pw를 대조해 값이 다르면,
            log.info(_request.getUserId() + "'s password is not matched.");// 로그를 찍을 자리
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다."); // pw가 일치하지 않는다고 하고 예외처리를 한다.
        }

        // jwtProvider 에서 access token 과 refresh token 을 만든다.
        String accessToken = jwtProvider.createAccessToken(user.getUserId());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        // 현재 auth 테이블에 userId 가 있는지 확인해 alUser 에 집어넣는다. == 유저가 페이지에 처음 로그인 하는 것인지 확인한다.
        Optional<Auth> alUser = authRepository.findByUserId(user.getId());

        if (!alUser.isPresent()) { // DB에 alUser 가 없으면 == 유저가 페이지에 처음 로그인하는 것이면
            Auth auth = new Auth(); // Auth 객체 하나를 새로 만들어서
            auth.setUserId(user.getId());
            auth.setRefreshToken(refreshToken); // userId 와 refresh token 을 지정해주고
            authRepository.save(auth); // DB에 집어넣는다.
        } else { // DB에 alUser 가 있으면 == 유저가 페이지에 처음 로그인 하는 것이 아니면
            alUser.ifPresent(selectUser -> {
               selectUser.setRefreshToken(refreshToken); // refresh token 을 갱신하고
               authRepository.save(selectUser); // DB에 집어넣는다.
            });
        }

        // 위에서의 결과로 나온 access token 과 refresh token 을 반환한다.
        TokenResponse token = new TokenResponse();
        token.setACCESS_TOKEN(accessToken);
        token.setREFRESH_TOKEN(refreshToken);
        return token;
    }

    /** access token 에 문제가 있으면 refresh token 을 통해 access token 을 재발급
     * refresh token 이 null 일경우 -> 재로그인
     * refresh token 이 유효할 경우 -> issuedAccessToken 을 통해 재발급
     * 이 함수에서는 access token 의 유효 여부에 관계 없이 refresh token 이 유효할 경우 재발급
     * */
    @Transactional
    public TokenResponse issuedAccessToken(HttpServletRequest _request) {
        // _request 에서 받아온 access token 과 refresh token 을 저장해둔다.
        String accessToken = jwtProvider.resolveAccessToken(_request);
        String refreshToken = jwtProvider.resolveRefreshToken(_request);

        if (jwtProvider.isValidAccessToken(refreshToken)) { // refresh token 이 유효하면
            // 로그를 찍을 자리
            Claims claims = jwtProvider.getClaimsFormRefreshToken(refreshToken); // jwt claims 객체 선언 후, refresh token 을 바탕으로 claim 을 찾아온다.
            Long id = Long.valueOf(claims.getId().toString()); // claim 에서 id를 가져와 long 형태로 바꾼다. 왜? user 의 id 가 long 이라서.
            Optional<User> user = userRepository.findById(id); // id 를 통해 user 를 가져온다,
            String tokenFromDB = authRepository.findByUserId(user.get().getId()).get().getRefreshToken(); // auth 에서 찾아온 user 의 id를 통해 userId 를 찾아와 refresh token 을 가져온다.

            if (refreshToken.equals(tokenFromDB)) { // 찾아온 refresh token 과 DB 에서 찾아온 토큰이 같으면
                accessToken = jwtProvider.createAccessToken(user.get().getUserId()); // access token 을 재발급해준다.
                log.info("ID : " + user.get().getUserId() + "'s Access Token is created."); // 로그를 찍을 자리
            } else { // 아니면?
                log.info("ID : " + user.get().getUserId() + "'s Token has issued."); // 로그를 찍을 자리
                accessToken = null; // 둘 다 null 로 만들어버린다.
                refreshToken = null;
            }
        }

        // 위에서의 결과로 나온 access token 과 refresh token 을 반환한다.
        TokenResponse token = new TokenResponse();
        token.setACCESS_TOKEN(accessToken);
        token.setREFRESH_TOKEN(refreshToken);
        return token;
    }

    /** access token 과 연동된 user 를 찾는 함수 */
    public User fineUser(HttpServletRequest _request) throws Exception {
        String accessToken = jwtProvider.resolveAccessToken(_request); // _request 에서 access token 을 받아온다.

        if (jwtProvider.isValidAccessToken(accessToken)) { // access token 이 유효하면
            Claims claims = jwtProvider.getClaimsFormAccessToken(accessToken); // jwt claims 객체 선언 후, access token 을 바탕으로 claim 을 찾아온다.
            String userId = (String) claims.get("userId"); // claim 에서 userId 를 찾아온다.
            User user = userRepository.findByUserId(userId) // user 객체를 선언해 userID 에 맞는 user 를 찾아온다.
                    .orElseThrow(() -> { // 없으면?
                        log.info(userId + " is not found.");// 로그를 찍을 자리
                        return new UsernameNotFoundException("존재하지 않는 사용자입니다."); // 예외 처리
                    });
            log.info(userId + "is be searched - return " + user.getUserId());// 로그를 찍을 자리
            return user; // 찾아온 유저를 반환해준다.
        } else { // access token 이 만료되었으면?
            log.info("Access token has expired.");// 로그를 찍을 자리
            throw new Exception("토큰이 만료되었습니다."); // 예외 처리
        }

    }
}
