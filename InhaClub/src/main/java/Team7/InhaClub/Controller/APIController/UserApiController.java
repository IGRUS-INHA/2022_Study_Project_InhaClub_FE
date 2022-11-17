package Team7.InhaClub.Controller.APIController;

import Team7.InhaClub.Domain.Dto.UserDto;
import Team7.InhaClub.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserApiController {

    private final UserService userService;

    /** 회원 탈퇴 */
    @DeleteMapping(value = "/user/userWithdraw")
    public ModelAndView userWithdraw(@RequestBody UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView();
        userService.userDelete(userDto);
        modelAndView.setViewName("/admin");
        return modelAndView;
    }

    /** 회원 정보 수정 */
    @PostMapping(value = "/user/userUpdate")
    public ModelAndView userUpdate(@RequestBody UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView();
        userService.userUpdate(userDto);
        modelAndView.setViewName("/admin");
        return modelAndView;
    }
}
