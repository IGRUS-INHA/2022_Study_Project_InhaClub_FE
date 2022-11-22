package Team7.InhaClub.Controller.APIController;

import Team7.InhaClub.Domain.Dto.RequestDto.ClubRequestDto;
import Team7.InhaClub.Domain.Dto.RequestDto.CommentsRequestDto;
import Team7.InhaClub.Domain.Dto.ResponseDto.ClubResponseDto;
import Team7.InhaClub.Domain.Entity.Club;
import Team7.InhaClub.Domain.Entity.Comments;
import Team7.InhaClub.Service.ClubService;
import Team7.InhaClub.Service.CommentsService;
import Team7.InhaClub.Service.PostsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class ClubApiController {
    private final ClubService clubService;
    private final CommentsService commentsService;

    /** 클럽 등록 */
    @PostMapping(value = "/club/clubRegister")
    public ResponseEntity<Club> registerNewClub(@RequestBody ClubRequestDto _dto) throws Exception {
        try {
            clubService.validateDuplicateClubName(_dto.getClubName());
            Club club = _dto.toEntity();

            Club responseClub = clubService.register(club);
            if (responseClub == null) {
                log.info("Failed to Register Club.");// 로그를 찍을 자리
                throw new Exception("Failed to Register Club.");
            } else {
                log.info("Club(" + club.getClubName() +") has be registered."); // 로그를 찍을 자리
                URI redirect = new URI("/");
                HttpHeaders loc = new HttpHeaders();
                loc.setLocation(redirect);
                return new ResponseEntity<>(loc, HttpStatus.MOVED_PERMANENTLY);
            }
        } catch (Exception e) {
            throw new Exception("Exception : " + e.toString());
        }
    }

    /** 클럽 삭제 */
    @DeleteMapping(value = "/club/clubDelete")
    public ModelAndView clubDelete(@RequestBody ClubRequestDto _dto) {
        System.out.println(_dto.getClubName());
        clubService.clubDelete(_dto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/adminPage.html");

        return modelAndView;
    }

    /** 수정 페이지로 동아리 데이터 전송 */
    @PostMapping(value = "/club/edit/{id}")
    public ClubResponseDto clubEditApi(@PathVariable("id") @ModelAttribute Long _id) {
        Club club = clubService.findByClubId(_id).orElseThrow(() -> new IllegalArgumentException("not found."));
        ClubResponseDto dto = new ClubResponseDto(club);

        return new ClubResponseDto(club);
    }

    /** 동아리 정보 수정 */
    @PostMapping(value = "/club/clubEdit")
    public ResponseEntity<String> clubEdit(@RequestBody ClubRequestDto _dto) {
        clubService.editClubInfo(_dto);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    /** QnA 저장 */
    @PostMapping(value = "/club/saveComment")
    public ResponseEntity<String> commentSave(@RequestBody CommentsRequestDto _dto) throws Exception {
        commentsService.save(_dto);
        return ResponseEntity.status(HttpStatus.OK).body("save success");
    }

    /** QnA 수정 */
    @PostMapping(value = "/club/modifyComment")
    public ResponseEntity<Comments> commentModify(@RequestBody CommentsRequestDto _dto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(commentsService.modify(_dto));
    }

    /** QnA 삭제 */
    @DeleteMapping("/club/deleteComment")
    public ResponseEntity<CommentsRequestDto> delete(@RequestBody CommentsRequestDto _dto) throws Exception {
        commentsService.delete(_dto);
        return ResponseEntity.status(HttpStatus.OK).body(_dto);
    }
}
