package Team7.InDaClub.Controller;

import Team7.InDaClub.Controller.Form.UserForm;
import Team7.InDaClub.Domain.User;
import Team7.InDaClub.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService _userService) {
        this.userService = _userService;
    }

    @GetMapping(value = "/users/auth")
    public String createForm() {
        return "/users/authForm";
    }

    @PostMapping(value = "/users/auth")
    public String create(UserForm form) {
        User tmpUser = new User(0L, form.getUserId(), form.getUserPw(), form.getUserNickname(), form.getUserEmail(), form.getUserPhone());
        userService.join(tmpUser);
        return "redirect:/";
    }

    @GetMapping(value = "/users")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("user", users);
        return "users/userList";
    }
}
