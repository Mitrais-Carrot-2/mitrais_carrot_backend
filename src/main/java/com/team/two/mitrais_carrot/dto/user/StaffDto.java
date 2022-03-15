package com.team.two.mitrais_carrot.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class StaffDto {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
//    private byte[] image;
    private String jobFamily;
    private String jobGrade;
//    private String office;
}
