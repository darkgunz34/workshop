package fr.workshop.web.dto.factory;

import fr.workshop.web.dto.CreationCompteDto;

public final class CreationCompteDtoFactory {

    private CreationCompteDtoFactory(){

    }

    public static CreationCompteDto getCreationCompteDtoEmpty(){
        return new CreationCompteDto();
    }
}
