package Team7.InhaClub.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    /** 에러 페이지 controller */
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int code = Integer.parseInt(status.toString());

            if (code == HttpStatus.NOT_FOUND.value()) return "/error/404";
            else {
                model.addAttribute("errorCode", status.toString());
                return "/error/error";
            }
        }
        else {
            model.addAttribute("errorCode", "Unknown Error!");
            return "/error/error";
        }
    }
}
