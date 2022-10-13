package Team7.InDaClub.Controller;

import Team7.InDaClub.Controller.Form.ClubForm;
import Team7.InDaClub.Domain.Entity.Club;
import Team7.InDaClub.Service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ClubController {
    private final ClubService clubService;

    @GetMapping(value = "/club/register")
    public String createForm() {
        return "/club/registerClubForm";
    }

    @PostMapping(value = "/club/register")
    public String create(ClubForm form) {
        //Club tmpClub = new Club(0L, form.getName(), form.getSns(), form.getInterest(), true, "2022-12-31", form.getRecruitTarget(), form.getRoom(), form.getRepresentative());
        //clubService.clubRegister(tmpClub);
        return "redirect:/";
    }

    @GetMapping(value = "/club")
    public String list(Model model) {
        List<Club> club = clubService.findClubs();
        model.addAttribute("club", club);
        return "club/clubList";
    }
}
