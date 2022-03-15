package com.team.two.mitrais_carrot.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferToStaffDto {
    private Long staffId;
    private Long carrotAmount;
    private Long rewardAmount;
    private Long sharedAmount;
    private Long bazaarAmount;
    private String note;
}
