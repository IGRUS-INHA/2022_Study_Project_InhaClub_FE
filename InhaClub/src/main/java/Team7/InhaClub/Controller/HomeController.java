package Team7.InhaClub.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /** 메인페이지 */
    @GetMapping(value = "/")
    public String main() {
        return "main";
    }

    /** 관리자 페이지 */
    @GetMapping(value = "/admin")
    public String adminPage() { return "/admin/adminPage"; }
}
