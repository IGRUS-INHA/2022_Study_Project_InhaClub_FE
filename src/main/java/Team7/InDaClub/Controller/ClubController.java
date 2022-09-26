package Team7.InDaClub.Controller;

import Team7.InDaClub.Controller.Form.ClubForm;
import Team7.InDaClub.Domain.Club;
import Team7.InDaClub.Repository.UserRepository;
import Team7.InDaClub.Service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService _clubService) {
        this.clubService = _clubService;
    }

    @GetMapping(value = "/clubs/register")
    public String createForm() {
        return "/clubs/registerClubForm";
    }

    @PostMapping(value = "/clubs/register")
    public String create(ClubForm form) {
        Club tmpClub = new Club(0L, form.getName(), form.getSns(), form.getInterest(), true, "2022-12-31", form.getRecruitTarget(), form.getRoom(), form.getRepresentative());
        clubService.clubRegister(tmpClub);
        return "redirect:/";
    }

    @GetMapping(value = "/clubs")
    public String list(Model model) {
        List<Club> clubs = clubService.findClubs();
        model.addAttribute("club", clubs);
        return "clubs/clubList";
    }
}
