package fr.workshop.web.dto.factory;

import fr.workshop.web.dto.LoginDto;

public final class LoginDtoFactory {

    private LoginDtoFactory(){

    }

    public static LoginDto getLoginDtoEmpty(){
        return new LoginDto();
    }
}
