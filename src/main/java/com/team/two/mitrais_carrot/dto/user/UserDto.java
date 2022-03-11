package com.team.two.mitrais_carrot.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private boolean id;
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate birthDate;
    private int supervisor_id;
    private int level;
}