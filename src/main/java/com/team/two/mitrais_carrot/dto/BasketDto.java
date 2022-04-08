package com.team.two.mitrais_carrot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketDto {
    private long userId;
    private long barnId;
    private long carrotAmount;
    private long rewardCarrot;
    private long shareCarrot;
    private long bazaarCarrot;
}

