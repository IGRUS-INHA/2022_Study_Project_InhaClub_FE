package Team7.InDaClub.Controller;

import Team7.InDaClub.Controller.Form.UserForm;
import Team7.InDaClub.Domain.Dto.UserDto;
import Team7.InDaClub.Domain.Entity.User;
import Team7.InDaClub.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService _userService) {
        this.userService = _userService;
    }

    /** 회원 리스트 */
    @GetMapping(value = "/user")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("user", users);
        return "user/userList";
    }

    /** 회원 탈퇴 */
    @RequestMapping(value = "/userWithdraw", method = RequestMethod.POST)
    public String userWithdraw(@RequestBody UserDto userDto) {
        userService.userDelete(userDto);
        return "redirect:/admin";
    }

    /** 회원 정보 수정 */
    @RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
    public String userUpdate(@RequestBody UserDto userDto) {
        userService.userUpdate(userDto);
        return "redirect:/admin";
    }
}
