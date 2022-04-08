package com.team.two.mitrais_carrot.dto.merchant;

import java.time.LocalDate;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class CreateBazaarDto{
    private String bazaarName;

    private LocalDate startDate;

    private LocalDate endDate;

}