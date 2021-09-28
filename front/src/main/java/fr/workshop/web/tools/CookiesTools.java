package fr.workshop.web.tools;

import fr.workshop.web.exception.CookiesException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public final class CookiesTools {

    public static final String COOKIES_NAME = "MyAuthentification";

    /**
     * expires in 1 hour
     */
    public static final int COOKIES_EXPIRATION_TIME_SECONDES = 3600;

    private CookiesTools(){

    }

    public static void createCookies(HttpServletResponse response, String codeToken){
        final Cookie cookie = new Cookie(COOKIES_NAME, codeToken);
        cookie.setMaxAge(COOKIES_EXPIRATION_TIME_SECONDES);
        response.addCookie(cookie);
    }

    public static String readCookies(HttpServletRequest req) throws CookiesException {
        String cookiesValue = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals(COOKIES_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
        if(cookiesValue != null){
            return cookiesValue ;
        }else{
            throw new CookiesException("Aucun cookie de présent sur le domaine");
        }
    }


}
