package com.team.two.mitrais_carrot.dto.farmer;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BarnEditDto {
    private String barnName;
    private Long carrotAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    
}
