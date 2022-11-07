package Team7.InDaClub.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check() {
        return "<h1>check</h1>";
    }
}
