package com.team.two.mitrais_carrot.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferToGroupDto {
    private Integer groupId;
    private Long carrotAmount;
    private String note;
}
