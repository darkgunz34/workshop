package fr.rallye.web.dto.factory;

import fr.rallye.web.dto.LoginDto;

public final class LoginDtoFactory {

    private LoginDtoFactory(){

    }

    public static LoginDto getLoginDtoEmpty(){
        return new LoginDto();
    }
}
