package fr.workshop.web.tools;

import fr.workshop.api.AppelApi;
import fr.workshop.exception.ApiException;
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


    public static void deleteCookies(HttpServletResponse response){
        final Cookie cookie = new Cookie(COOKIES_NAME, null);
        cookie.setMaxAge(1);
        response.addCookie(cookie);
    }

    public static String readCookies(final HttpServletRequest req) throws CookiesException {
        Cookie[] cookies = req.getCookies();

        if(cookies == null){
            throw new CookiesException("Aucun cookie de présent dans la requête");
        }else{
            String cookiesValue = recuperationCookies(cookies);

            if(cookiesValue != null) {
                return cookiesValue;
            }
        }
        throw new CookiesException("Aucun cookie de présent dans la requête");
    }

    private static String recuperationCookies(final Cookie[] cookies){
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(COOKIES_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

    public static boolean checkClientConnecter(HttpServletRequest req){
        try{
            String token = CookiesTools.readCookies(req);
            if(token.isEmpty()){
                return false;
            }
            AppelApi.authentificationFromToken(token);
            return true;
        }catch (CookiesException | ApiException e){
            return false;
        }
    }


}
