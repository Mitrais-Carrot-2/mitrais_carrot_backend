package com.team.two.mitrais_carrot.dto.merchant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffGroupDto {
    private int id;
    private String name;
    private Long allocation;
    //private Integer totalMember;
    //private Long total;
    private String note;
    private Integer managerId;
}
