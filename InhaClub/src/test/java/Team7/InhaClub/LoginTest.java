package Team7.InhaClub;

import Team7.InhaClub.Domain.Dto.LoginDto;
import Team7.InhaClub.Domain.Entity.Auth;
import Team7.InhaClub.Domain.Entity.User;
import Team7.InhaClub.Repository.AuthRepository;
import Team7.InhaClub.Repository.UserRepository;
import Team7.InhaClub.Security.JwtProvider;
import lombok.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@SpringBootTest
@RequiredArgsConstructor
public class LoginTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Test
    public void doLogin() throws Exception {
        for (int i = 0; i < 10; i++) {
            LoginDto userRequest = new LoginDto("case" + i, i + "q2w3e4r!");

            User user = userRepository.findByUserId(userRequest.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

            if (!passwordEncoder.matches(userRequest.getUserPw(), user.getPassword())) {
                throw new Exception("비밀번호가 일치하지 않습니다.");
            }

            String accessToken = jwtProvider.createAccessToken(user.getUserId());
            String refreshToken = jwtProvider.createRefreshToken(user.getId());

            Optional<Auth> alUser = authRepository.findByUserId(user.getId());

            System.out.println(alUser);
            if (!alUser.isPresent()) {
                // 최초 로그인 -> 새로 발급
                Auth auth = new Auth();
                auth.setUserId(user.getId());
                auth.setRefreshToken(refreshToken);
                authRepository.save(auth);
            } else {
                // 해당 ID의 refreshToken update;
                alUser.ifPresent(selectUser -> {
                    selectUser.setRefreshToken(refreshToken);
                    authRepository.save(selectUser);
                });
            }

            System.out.println(accessToken);
        }
    }

}
