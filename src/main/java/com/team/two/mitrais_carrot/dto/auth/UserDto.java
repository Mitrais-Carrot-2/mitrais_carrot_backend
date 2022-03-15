package com.team.two.mitrais_carrot.dto.auth;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String email;
    private long carrot;
//    private String firstName;
//    private String lastName;
//    private String address;
//    private LocalDate birthDate;
//    private int supervisorId;
//    private int level;
}