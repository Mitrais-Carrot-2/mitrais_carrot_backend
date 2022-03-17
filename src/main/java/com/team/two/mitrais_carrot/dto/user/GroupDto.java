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

    public GroupDto(int id, String name, Long allocation, Integer totalMember, Long total, String note) {
        this.id = id;
        this.name = name;
        this.allocation = allocation;
        this.totalMember = totalMember;
        this.total = total;
        this.note = note;
    }
}
