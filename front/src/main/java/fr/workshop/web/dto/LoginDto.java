package fr.rallye.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class LoginDto {

    @Getter
    @Setter
    String login;

    @Getter
    @Setter
    String password;

}
