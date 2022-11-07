package Team7.InDaClub.Controller;

import Team7.InDaClub.Domain.Dto.EmailRequestDto;
import Team7.InDaClub.Domain.Dto.JoinDto;
import Team7.InDaClub.Domain.Dto.LoginDto;
import Team7.InDaClub.Domain.Dto.TokenResponseDto;
import Team7.InDaClub.Domain.Entity.User;
import Team7.InDaClub.Service.AuthService;
import Team7.InDaClub.Service.EmailAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
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
    public ModelAndView createAuthForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/authForm");
        return modelAndView;
    }

    @GetMapping(value = "/user/login")
    public ModelAndView createLoginForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/loginForm");
        return modelAndView;
    }

    /** 회원가입 */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity join(@RequestBody JoinDto joinDto, HttpServletResponse response) throws Exception {
        User user = joinDto.toEntity();

        User responseUser = authService.join(user);
        if (responseUser == null) {
            log.info("Failed to join user.");// 로그를 찍을 자리
            throw new Exception("회원 저장에 실패했습니다.");
        } else {
            log.info("User(" + user.getUserId() +") has be registered."); // 로그를 찍을 자리
            response.sendRedirect("/main");
            return ResponseEntity.status(HttpStatus.OK).body(responseUser);
        }
    }

    /** id 중복 검사 */
    @RequestMapping(value = "/auth/checkDuplicatedId", method = RequestMethod.POST)
    public Boolean checkDuplicatedId(@RequestBody JoinDto joinDto) {
        return authService.chkDuplicatedId(joinDto.getUserId());
    }

    /** Email 중복 검사 */
    @RequestMapping(value = "/auth/checkDuplicatedEmail", method = RequestMethod.POST)
    public Boolean checkDuplicatedEmail(@RequestBody JoinDto joinDto) {
        return authService.chkDuplicatedId(joinDto.getUserEmail());
    }

    /** 인증 이메일 보내기 */
    @RequestMapping(value = "/auth/mailConfirm", method = RequestMethod.POST)
    public ResponseEntity emailConfirm(@RequestBody EmailRequestDto emailRequestDto, HttpServletResponse response) throws Exception {
        if (authService.chkDuplicatedEmail(emailRequestDto.getUserEmail())) {
            return null;
        }
        else {
            HttpHeaders headers = new HttpHeaders();
            String code = emailAuthService.sendSimpleMessage(emailRequestDto.getUserEmail());
            setEmailAuthCode(code);
            log.info("email chk : " + emailRequestDto.getUserEmail());
            headers.setLocation(URI.create("/"));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }
    }

    /** 이메일 인증 */
    @RequestMapping(value = "/auth/mailConfirmChk", method = RequestMethod.POST)
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

    /** 로그인 */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginDto loginDto) throws Exception {
        System.out.println(loginDto.getUserId() + ", " + loginDto.getUserPw());
        TokenResponseDto token = authService.doLogin(loginDto);
        log.info("login - " + loginDto.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}