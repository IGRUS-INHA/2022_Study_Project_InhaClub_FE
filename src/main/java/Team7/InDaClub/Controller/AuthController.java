package Team7.InDaClub.Controller;

import Team7.InDaClub.Domain.Dto.JoinDto;
import Team7.InDaClub.Domain.Dto.LoginDto;
import Team7.InDaClub.Domain.Dto.TokenResponse;
import Team7.InDaClub.Domain.Entity.User;
import Team7.InDaClub.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController (AuthService _authService) {
        this.authService = _authService;
    }

    @GetMapping(value = "/user/auth")
    public String createAuthForm() { return "/user/authForm"; }

    /** 회원가입 */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<User> join(@RequestBody JoinDto joinDto) throws Exception {
        User user = User.builder()
                .userId(joinDto.getUserId())
                .userPw(joinDto.getUserPw())
                .userNickname(joinDto.getUserNickname())
                .userEmail(joinDto.getUserEmail())
                .userPhone(joinDto.getUserPhone())
                .build();


        User responseUser = authService.join(user);
        if (responseUser == null) {
            // 로그를 찍을 자리
            throw new Exception("회원 저장에 실패했습니다.");
        } else {
            // 로그를 찍을 자리
            return ResponseEntity.status(HttpStatus.OK).body(responseUser);
        }
    }

    /** 로그인 */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginDto loginDto) throws Exception {
        TokenResponse token = authService.doLogin(loginDto);
        // 로그를 찍는 자리
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

   // @PostMapping("/accessToken")

}
