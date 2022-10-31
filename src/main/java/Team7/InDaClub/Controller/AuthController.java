package Team7.InDaClub.Controller;

import Team7.InDaClub.Domain.Dto.EmailRequestDto;
import Team7.InDaClub.Domain.Dto.JoinDto;
import Team7.InDaClub.Domain.Dto.LoginDto;
import Team7.InDaClub.Domain.Dto.TokenResponse;
import Team7.InDaClub.Domain.Entity.User;
import Team7.InDaClub.Service.AuthService;
import Team7.InDaClub.Service.EmailAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final EmailAuthService emailAuthService;
    private String emailAuthCode = "a";

    public String getEmailAuthCode() {
        return emailAuthCode;
    }

    public void setEmailAuthCode(String emailAuthCode) {
        this.emailAuthCode = emailAuthCode;
    }

    @GetMapping(value = "/user/auth")
    public String createAuthForm() {
        return "/user/authForm";
    }

    @GetMapping(value = "/user/login")
    public String createLoginForm() {
        return "/user/loginForm";
    }

    /** 회원가입 */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String join(@RequestBody JoinDto joinDto) throws Exception {
        User user = User.builder()
                .userId(joinDto.getUserId())
                .userPw(joinDto.getUserPw())
                .userNickname(joinDto.getUserNickname())
                .userEmail(joinDto.getUserEmail())
                .userPhone(joinDto.getUserPhone())
                .build();

        User responseUser = authService.join(user);
        if (responseUser == null) {
            log.info("Failed to join user.");// 로그를 찍을 자리
            throw new Exception("회원 저장에 실패했습니다.");
        } else {
            log.info("User(" + user.getUserId() +") has be registered.");// 로그를 찍을 자리
            //return ResponseEntity.status(HttpStatus.OK).body(responseUser); 함수의 return 이 ResponseEntity<User> 일때 썼던 것
            return "redirect:/main";
        }
    }

    /** 로그인 */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(LoginDto loginDto) throws Exception {
        TokenResponse token = authService.doLogin(loginDto);
        log.info("login - " + loginDto.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    /** 인증 이메일 보내기 */
    @RequestMapping(value = "/mailConfirm", method = RequestMethod.POST)
    public String emailConfirm(@RequestBody EmailRequestDto emailRequestDto) throws Exception {
        String code = emailAuthService.sendSimpleMessage(emailRequestDto.getUserEmail());
        setEmailAuthCode(code);
        log.info("email chk : " + emailRequestDto.getUserEmail());
        return "redirect:/";
    }

    /** 이메일 인증 */
    @RequestMapping(value = "mailConfirmChk", method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkEmailConfirm(@RequestBody EmailRequestDto emailRequestDto) throws Exception {
      if (getEmailAuthCode().equals(emailRequestDto.getCode())) {
          log.info(emailAuthCode + ", " + emailRequestDto.getCode() + "check success");
          return true;
      } else {
          log.info(emailAuthCode + ", " + emailRequestDto.getCode() + "check failed");
          return false;
      }
    }
}
