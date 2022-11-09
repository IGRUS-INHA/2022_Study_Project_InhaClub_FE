package Team7.InhaClub.Controller;

import Team7.InhaClub.Domain.Dto.EmailRequestDto;
import Team7.InhaClub.Domain.Dto.JoinDto;
import Team7.InhaClub.Domain.Dto.LoginDto;
import Team7.InhaClub.Domain.Dto.TokenResponseDto;
import Team7.InhaClub.Domain.Entity.User;
import Team7.InhaClub.Service.AuthService;
import Team7.InhaClub.Service.EmailAuthService;
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

}
