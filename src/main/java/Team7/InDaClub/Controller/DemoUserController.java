package Team7.InDaClub.Controller;

import Team7.InDaClub.Domain.User;
import Team7.InDaClub.Service.DemoUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DemoUserController {

    private DemoUserService demoUserService = new DemoUserService();

    public DemoUserController(DemoUserService _demoUserService) {
        this.demoUserService = _demoUserService;
    }

    @GetMapping(value = "/users/new")
    public String createForm() {
        return "/users/createUserForm";
    }

    @PostMapping(value = "/users/new")
    public String create(UserForm form) {
        User tmpUser = new User();
        tmpUser.setUserId(form.getUserId());
        tmpUser.setUserPw(form.getUserPw());
        tmpUser.setUserNickname(form.getUserNickname());
        tmpUser.setUserEmail(form.getUserEmail());
        tmpUser.setUserPhone(form.getUserPhone());

        demoUserService.join(tmpUser);
        return "redirect:/";
    }

    @GetMapping(value = "/users")
    public String list(Model model) {
        List<User> users = demoUserService.findUsers();
        model.addAttribute("user", users);
        return "users/userList";
    }

}
