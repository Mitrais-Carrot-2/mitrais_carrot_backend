package com.team.two.mitrais_carrot.dto.farmer;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBarnDto {
    private String barnName;
    private Long carrotAmount;
    private LocalDate startDate;
    private LocalDate expiredDate;
    private Boolean isActive;

}