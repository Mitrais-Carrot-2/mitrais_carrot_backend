package com.team.two.mitrais_carrot.dto.farmer;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//    @JsonProperty("barn_id")
//    private Integer barnId;
    @JsonProperty("barn_name")
    private String barnName;
//    @JsonProperty("barn_owner")
//    private String barnOwner;
//    @JsonProperty("barn_period")
//    private String barnPeriod;
    @JsonProperty("carrot_amount")
    private Long carrotAmount;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
//    private Boolean isActive;

    // public BarnDto(String barnName, Long carrotAmount, LocalDate startDate, LocalDate endDate) {
    //     this.barnName = barnName;
    //     this.carrotAmount = carrotAmount;
    //     this.startDate = startDate;
    //     this.endDate = endDate;
    // }
}