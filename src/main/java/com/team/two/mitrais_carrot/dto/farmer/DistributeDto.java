package com.team.two.mitrais_carrot.dto.farmer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistributeDto {
    private Long managerId;
    private Long carrotAmount;
    private int barnId;
    private String note;
    
}
