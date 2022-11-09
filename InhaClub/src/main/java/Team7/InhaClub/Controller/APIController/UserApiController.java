package Team7.InhaClub.Controller.APIController;

import Team7.InhaClub.Domain.Dto.UserDto;
import Team7.InhaClub.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserApiController {

    private final UserService userService;

    /** 회원 탈퇴 */
    @RequestMapping(value = "/user/userWithdraw", method = RequestMethod.POST)
    public ModelAndView userWithdraw(@RequestBody UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView();
        userService.userDelete(userDto);
        modelAndView.setViewName("/admin");
        return modelAndView;
    }

    /** 회원 정보 수정 */
    @RequestMapping(value = "/user/userUpdate", method = RequestMethod.POST)
    public ModelAndView userUpdate(@RequestBody UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView();
        userService.userUpdate(userDto);
        modelAndView.setViewName("/admin");
        return modelAndView;
    }
}
