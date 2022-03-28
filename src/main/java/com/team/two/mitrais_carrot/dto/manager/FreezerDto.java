package com.team.two.mitrais_carrot.dto.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreezerDto {
    @JsonProperty("freezer_id")
    private Integer freezerId;
    @JsonProperty("barn_name")
    private String barnName;
    @JsonProperty("barn_owner")
    private String barnOwner;
    @JsonProperty("barn_period")
    private String barnPeriod;
    @JsonProperty("carrot_amount")
    private Long carrotAmount;
    @JsonProperty("distributed_carrot")
    private Long distributedCarrot;
}
