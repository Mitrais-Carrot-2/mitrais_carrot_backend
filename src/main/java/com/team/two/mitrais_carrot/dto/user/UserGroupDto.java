package com.team.two.mitrais_carrot.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupDto {
    private String username;
    private String firstName;
    private String lastName;
    private String jobFamily;
    private String jobGrade;
    private String office;
}
