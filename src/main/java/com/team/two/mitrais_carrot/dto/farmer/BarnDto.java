package com.team.two.mitrais_carrot.dto.farmer;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarnDto {
    @JsonProperty("barn_name")
    private String barnName;
    @JsonProperty("carrot_amount")
    private Long carrotAmount;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
//    private Boolean isActive;

}