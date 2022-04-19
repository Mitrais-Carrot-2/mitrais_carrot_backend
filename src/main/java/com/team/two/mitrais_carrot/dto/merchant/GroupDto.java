package com.team.two.mitrais_carrot.dto.merchant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private int id;
    private String name;
    private Long allocation;
    private Integer totalMember;
    private Long total;
    private String note;
}
