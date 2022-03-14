package com.team.two.mitrais_carrot.dto.farmer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetBirthdayCarrotDto {
    @JsonProperty("carrot_amount")
    private int carrotAmount;

}
