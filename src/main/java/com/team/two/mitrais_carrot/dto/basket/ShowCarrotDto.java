package com.team.two.mitrais_carrot.dto.basket;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.service.user.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShowCarrotDto {
    long userId;
    BasketEntity basket;

    public ShowCarrotDto(long userId, BasketEntity basket){
        this.userId = userId;
        this.basket = basket;
    }

}
