package fr.workshop.api;

import fr.workshop.exception.ApiException;
import fr.workshop.web.dto.CreationCompteDto;
import fr.workshop.web.dto.LoginDto;

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

    public static void creationCompte(final CreationCompteDto creationCompteDto) throws ApiException {
//        throw new ApiException("Token non valide");
    }

}
