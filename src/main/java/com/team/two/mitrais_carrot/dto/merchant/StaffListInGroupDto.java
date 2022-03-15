package com.team.two.mitrais_carrot.dto.merchant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffListInGroupDto {
    //username, name (first + last), jf, grade, office

    private String username;

    private String name;

    private String jf;

    private String grade;

    private String office;

    public StaffListInGroupDto(String username, String s, String jobFamily, String jobGrade, String office) {
        this.username = username;
        this.name = s;
        this.jf = jobFamily;
        this.grade = jobGrade;
        this.office = office;
    }
}
