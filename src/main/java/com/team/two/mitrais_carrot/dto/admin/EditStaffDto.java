package com.team.two.mitrais_carrot.dto.admin;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class EditStaffDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private long supervisorId;
    private boolean enableNotif;
    private String jobFamily;
    private String jobGrade;
    private String office;
}
