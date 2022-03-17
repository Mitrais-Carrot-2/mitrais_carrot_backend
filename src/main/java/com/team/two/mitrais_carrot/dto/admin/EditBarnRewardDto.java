package com.team.two.mitrais_carrot.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditBarnRewardDto {
    @JsonProperty("reward_description")
    private String rewardDescription;

    @JsonProperty("carrot_amount")
    private Long carrotAmount;

}
