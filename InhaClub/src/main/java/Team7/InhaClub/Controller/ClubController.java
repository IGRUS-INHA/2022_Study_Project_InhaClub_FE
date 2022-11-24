package Team7.InhaClub.Controller;

import Team7.InhaClub.Domain.Dto.ResponseDto.ClubResponseDto;
import Team7.InhaClub.Domain.Dto.ResponseDto.CommentsResponseDto;
import Team7.InhaClub.Domain.Dto.ResponseDto.PostResponseDto;
import Team7.InhaClub.Domain.Entity.Club;
import Team7.InhaClub.Service.ClubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ClubController {
    private final ClubService clubService;

    /** 클럽 등록 페이지로 이동 */
    @GetMapping(value = "/club/register")
    public String createForm() {
        return "/club/registerClubForm";
    }

    /** 클럽 리스트 페이지 이동 */
    @GetMapping(value = "/club")
    public String list(Model model) {
        List<Club> clubs = clubService.findClubs();
        model.addAttribute("club", clubs);

        return "club/clubList";
    }

    /** 클럽 상세정보 페이지 이동 */
    @GetMapping(value = "/club/{id}")
    public String clubPage(@PathVariable("id") @ModelAttribute("id") Long _id, Model model) {
        Club club = clubService.findByClubId(_id).orElseThrow(() -> new IllegalArgumentException("not found."));
        ClubResponseDto response = new ClubResponseDto(club);
        PostResponseDto dto = new PostResponseDto(club.getPosts());
        List<CommentsResponseDto> comments = dto.getComments();

        model.addAttribute("club", response);
        model.addAttribute("posts", dto);
        model.addAttribute("comments", comments);

        return "/club/clubContents";
    }

    /** 클럽 정보 수정 페이지 이동 */
    @GetMapping(value = "/club/{id}/edit")
    public String clubEdit(@PathVariable("id") @ModelAttribute("id") Long _id, Model model) {
        model.addAttribute("_id", _id);
        return "/club/clubEdit";
    }

}
