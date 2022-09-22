package Team7.InDaClub.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @GetMapping("/")
    @ResponseBody
    public String main(Model _model) {
        _model.addAttribute("data", "hello");
        return "main";
    }

}
