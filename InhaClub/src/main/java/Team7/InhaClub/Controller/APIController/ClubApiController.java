package Team7.InhaClub.Controller.APIController;

import Team7.InhaClub.Domain.Dto.ClubDto;
import Team7.InhaClub.Domain.Dto.CommentsRequestDto;
import Team7.InhaClub.Domain.Entity.Club;
import Team7.InhaClub.Domain.Entity.Comments;
import Team7.InhaClub.Service.ClubService;
import Team7.InhaClub.Service.CommentsService;
import Team7.InhaClub.Service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class ClubApiController {
    private final ClubService clubService;
    private final PostsService postsService;
    private final CommentsService commentsService;

    @RequestMapping(value = "/club/saveComment", method = RequestMethod.POST)
    public ResponseEntity<Comments> commentSave(@RequestBody CommentsRequestDto commentsDto, HttpServletResponse response) throws Exception {
        commentsDto.setPosts(postsService.findById(commentsDto.getPostsId()).orElseThrow(() -> new IllegalArgumentException("Rrror!")));
        commentsService.save(commentsDto);
        return ResponseEntity.status(HttpStatus.OK).body(commentsService.findById(commentsDto.getId()).orElseThrow(() -> new IllegalArgumentException("Error!")));

    }

    /** 클럽 등록 */
    @RequestMapping(value = "/club/registerNewClub", method = RequestMethod.POST)
    public ResponseEntity<Club> registerNewClub(@RequestBody ClubDto clubDto, HttpServletResponse response) throws Exception {
        Club club = clubDto.toEntity();

        Club responseClub = clubService.register(club);
        if (responseClub == null) {
            log.info("Failed to Register Club.");// 로그를 찍을 자리
            throw new Exception("Failed to Register Club.");
        } else {
            log.info("Club(" + club.getClubName() +") has be registered."); // 로그를 찍을 자리
            response.sendRedirect("/main");
            return ResponseEntity.status(HttpStatus.OK).body(responseClub);
        }
    }

    @RequestMapping(value = "/club/clubDelete", method = RequestMethod.POST)
    public ModelAndView clubDelete(@RequestBody ClubDto clubDto) {
        clubService.clubDelete(clubDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin");

        return modelAndView;
    }
}
