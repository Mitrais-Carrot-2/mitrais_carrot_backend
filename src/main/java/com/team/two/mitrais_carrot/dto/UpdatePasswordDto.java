package com.team.two.mitrais_carrot.dto;

import lombok.*;

@Getter
@Setter
public class UpdatePasswordDto {
    private String oldPassword;
    private String newPassword;
}
