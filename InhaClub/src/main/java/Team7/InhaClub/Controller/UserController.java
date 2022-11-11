package Team7.InhaClub.Controller;

import Team7.InhaClub.Domain.Entity.User;
import Team7.InhaClub.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    /** 회원 리스트 페이지로 이동 */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("user", users);

        return "/user/userList";
    }

}
