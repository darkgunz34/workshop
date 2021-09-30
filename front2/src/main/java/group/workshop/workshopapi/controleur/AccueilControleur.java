package group.workshop.workshopapi.controleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping(value = {"/acceuil", "/"})
public class AccueilControleur {

    final static String COOKIES_NAME = "MyAuthentification";

    @GetMapping
    public static ModelAndView GetAcceuil(final HttpServletRequest req) {
        return new ModelAndView(readCookies(req) ? "Authentified" : "NotAuthentified");
    }

    public static Boolean readCookies(final HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        return cookies != null && recuperationCookies(cookies) != null ? true : false;
    }


    private static String recuperationCookies(final Cookie[] cookies){
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(COOKIES_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }
}
