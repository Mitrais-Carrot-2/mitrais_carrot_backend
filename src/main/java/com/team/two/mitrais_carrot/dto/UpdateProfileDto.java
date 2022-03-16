package com.team.two.mitrais_carrot.dto;

import lombok.*;

@Getter
@Setter
public class UpdateProfileDto {
    private String currentPassword;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
}
