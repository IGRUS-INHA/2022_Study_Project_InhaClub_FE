package Team7.InDaClub.Controller;

import Team7.InDaClub.Controller.Form.UserForm;
import Team7.InDaClub.Domain.Entity.User;
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

    @GetMapping(value = "/user")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("user", users);
        return "user/userList";
    }
}
