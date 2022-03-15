package com.team.two.mitrais_carrot.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.two.mitrais_carrot.entity.admin.ETypeBarnReward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarnRewardDto {
    @JsonProperty("reward_decription")
    private String rewardDescription;
    
    @JsonProperty("carrot_amount")
    private int carrotAmount;

    @JsonProperty("giving_conditional")
    private ETypeBarnReward givingConditional;

}
