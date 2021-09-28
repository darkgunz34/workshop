package fr.workshop.api;

import fr.workshop.exception.ApiException;
import fr.workshop.web.dto.LoginDto;
import fr.workshop.web.tools.CookiesTools;

public final class AppelApi {

    private AppelApi(){

    }

    public static String authentificationFromLoginPassword(final LoginDto loginDto) throws ApiException {
//        throw new ApiException("Tocken invalide");
        return "myTocken";
    }

    public static void authentificationFromToken(final String token) throws ApiException {
//        throw new ApiException("Token non valide");
    }

}
