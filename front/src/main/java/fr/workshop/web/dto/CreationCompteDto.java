package fr.workshop.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CreationCompteDto {

    @Getter
    @Setter
    String login;

    @Getter
    @Setter
    String password;

    @Getter
    @Setter
    String passwordConfirme;

    @Getter
    @Setter
    String mail;
}
