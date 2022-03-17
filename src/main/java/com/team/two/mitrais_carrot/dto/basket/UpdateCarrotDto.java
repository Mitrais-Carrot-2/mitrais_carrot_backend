package com.team.two.mitrais_carrot.dto.basket;

import com.team.two.mitrais_carrot.service.basket.EBasket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarrotDto {
    private long userId;
    private long addCarrot;
    private EBasket transferType;
}
