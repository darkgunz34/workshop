package fr.rallye.web.tools;

import fr.rallye.web.exception.CookiesException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public final class CookiesTools {

    public static final String COOKIES_NAME = "MyAuthentification";

    private CookiesTools(){

    }

    public static void createCookies(HttpServletResponse response, String codeToken){
        final Cookie cookie = new Cookie(COOKIES_NAME, codeToken);
        cookie.setMaxAge(3600); // expires in 1 hour
        response.addCookie(cookie);
    }

    public static String readCookies(HttpServletRequest req) throws CookiesException {
        return Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals(COOKIES_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }


}
