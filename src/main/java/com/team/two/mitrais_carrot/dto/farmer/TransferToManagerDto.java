package com.team.two.mitrais_carrot.dto.farmer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferToManagerDto {
    @JsonProperty("manager_id")
    private Long managerId;
//    @JsonProperty("barn_id")
//    private Long barnId;
    @JsonProperty("carrot_amount")
    private Long carrotAmount;
}