package group.workshop.controleur;

import group.workshop.api.AppelApi;
import group.workshop.exception.ApiException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping(value = {"/acceuil", "/"})
public class AccueilControleur {

    private static final String COOKIES_NAME = "MyAuthentification";

    @GetMapping
    public static ModelAndView GetAcceuil(final HttpServletRequest req) {
        try{
            Cookie[] cookies = req.getCookies();
            if(Boolean.TRUE.equals(readCookies(cookies))){
                String token = recuperationCookies(cookies);
                AppelApi.authentificationFromToken(token);
                return new ModelAndView("Authentified");
            }
        }catch(ApiException e){
            return new ModelAndView("NotAuthentified");
        }
        return new ModelAndView("NotAuthentified");
    }

    public static Boolean readCookies(final Cookie[] cookies) {
        return cookies != null && recuperationCookies(cookies) != null;
    }


    private static String recuperationCookies(final Cookie[] cookies){
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(COOKIES_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }
}
