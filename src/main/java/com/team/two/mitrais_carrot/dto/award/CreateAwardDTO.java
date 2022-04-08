package com.team.two.mitrais_carrot.dto.award;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateAwardDTO {
    private boolean isActive;

    private String name;

    private String description;

    private Long carrotAmount;

    private LocalDate expiryDate;
}
