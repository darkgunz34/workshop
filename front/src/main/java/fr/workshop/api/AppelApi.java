package fr.rallye.api;

import fr.rallye.exception.ApiException;
import fr.rallye.web.dto.LoginDto;

public final class AppelApi {

    private AppelApi(){

    }

    public static String authentificationFromLoginPassword(final LoginDto loginDto) throws ApiException {
//        throw new ApiException("Tocken invalide");
        return "myTocken";
    }

}
