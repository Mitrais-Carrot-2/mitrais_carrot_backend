package com.team.two.mitrais_carrot.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SignUpDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String gender;
    private LocalDate birthDate;
    private long supervisorId;
    private String jobFamily;
    private String jobGrade;
    private String office;
    private Set<String> role;
}
