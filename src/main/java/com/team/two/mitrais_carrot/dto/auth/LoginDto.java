package com.team.two.mitrais_carrot.dto.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginDto {
    @Setter @Getter
    private String username;
    @Setter @Getter
    private String password;
}
