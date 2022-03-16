package com.team.two.mitrais_carrot.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class GroupDto {
    private int id;
    private String name;
    private Long allocation;
    private Integer totalMember;
    private Long total;
    private String note;
    private List<StaffDto> staff;
}
