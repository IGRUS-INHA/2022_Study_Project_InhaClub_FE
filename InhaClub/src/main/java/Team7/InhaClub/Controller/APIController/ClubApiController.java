package Team7.InhaClub.Controller.APIController;

import Team7.InhaClub.Domain.Dto.ClubDto;
import Team7.InhaClub.Domain.Dto.RequestDto.CommentsRequestDto;
import Team7.InhaClub.Domain.Entity.Club;
import Team7.InhaClub.Domain.Entity.Comments;
import Team7.InhaClub.Service.ClubService;
import Team7.InhaClub.Service.CommentsService;
import Team7.InhaClub.Service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.Comment;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class ClubApiController {
    private final ClubService clubService;
    private final PostsService postsService;
    private final CommentsService commentsService;

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
            URI redirect = new URI("/");
            HttpHeaders loc = new HttpHeaders();
            loc.setLocation(redirect);
            return new ResponseEntity<Club>(loc, HttpStatus.MOVED_PERMANENTLY);
        }
    }

    /** 클럽 삭제 */
    @RequestMapping(value = "/club/clubDelete", method = RequestMethod.POST)
    public ModelAndView clubDelete(@RequestBody ClubDto clubDto) {
        System.out.println(clubDto.getClubName());
        clubService.clubDelete(clubDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/adminPage.html");

        return modelAndView;
    }

    /** 댓글 저장 */
    @RequestMapping(value = "/club/saveComment", method = RequestMethod.POST)
    public ResponseEntity<Comments> commentSave(@RequestBody CommentsRequestDto _dto) throws Exception {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        _dto.setPosts(postsService.findById(_dto.getPostsId()).orElseThrow(() -> new IllegalArgumentException("not found.")));
        _dto.setCreatedDate(ldt.format(dft));
        _dto.setModifiedDate(ldt.format(dft));
        System.out.println(_dto.getUserName());
        if (Objects.equals(_dto.getUserName(), "")) {
            _dto.setUserName(commentsService.makeAnonymous());
        }

        Comments comments = _dto.toEntity();
        commentsService.save(comments);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @DeleteMapping("/club/deleteComments")
    public ResponseEntity<Long> delete(@RequestBody CommentsRequestDto dto) {
        Long _id = dto.getId();
        Comments comments = commentsService.findById(_id).orElseThrow(() -> new IllegalArgumentException("id not found."));
        System.out.println(_id);
        commentsService.delete(comments);
        return ResponseEntity.ok(_id);
    }
}
