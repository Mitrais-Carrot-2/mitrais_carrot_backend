package com.team.two.mitrais_carrot.controller.exchange;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.basket.EBasket;
import com.team.two.mitrais_carrot.service.exchange.ExchangeService;
import com.team.two.mitrais_carrot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {
    @Autowired
    ExchangeService exchangeService;

    BasketService basketService;
    UserService userService;

    @PostMapping("")
    public ExchangeService.ExchangeStatus buyItem(@RequestParam(value = "User ID") long userId, @RequestParam(value = "Item ID") int itemId){
//        UserEntity user = userService.getById(userId);
//        basketService.updateCarrot(user, 300, EBasket.REWARD);

        return exchangeService.buyBazaarItem(userId, itemId);
    }

}
