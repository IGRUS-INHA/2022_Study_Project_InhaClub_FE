package Team7.InDaClub.Controller;

import Team7.InDaClub.Domain.Dto.ClubDto;
import Team7.InDaClub.Domain.Entity.Club;
import Team7.InDaClub.Service.ClubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ClubController {
    private final ClubService clubService;

    /** 클럽 등록 페이지로 이동 */
    @RequestMapping(value = "/club/register", method = RequestMethod.GET)
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/club/registerClubForm");

        return modelAndView;
    }

    /** 클럽 리스트 페이지로 이동 */
    @RequestMapping(value = "/club", method = RequestMethod.GET)
    public ModelAndView list(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("club/clubList");
        List<Club> clubs = clubService.findClubs();
        model.addAttribute("club", clubs);
        modelAndView.addObject(model);

        return modelAndView;
    }

    /** 클럽 등록 */
    @RequestMapping(value = "/club/registerNewClub", method = RequestMethod.POST)
    public ResponseEntity registerNewClub(@RequestBody ClubDto clubDto, HttpServletResponse response) throws Exception{
        Club club = clubDto.toEntity();

        Club responseClub = clubService.register(club);
        if (responseClub == null) {
            log.info("Failed to Register Club.");// 로그를 찍을 자리
            throw new Exception("Failed to Register Club.");
        } else {
            log.info("Club(" + club.getName() +") has be registered."); // 로그를 찍을 자리
            response.sendRedirect("/main");
            return ResponseEntity.status(HttpStatus.OK).body(responseClub);
        }
    }


}
