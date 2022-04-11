package com.team.two.mitrais_carrot.controller.basket;

import com.team.two.mitrais_carrot.dto.basket.ShowCarrotDto;
import com.team.two.mitrais_carrot.dto.basket.UpdateCarrotDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketController {
    @Autowired
    BasketService basketService;

    @Autowired
    UserService userService;

    @PostMapping("")
    public BasketEntity addBasket(UserEntity userId){
        UserEntity user = userService.getById(userId.getId());
        return basketService.add(userId);
    }

    @PutMapping("carrot")
    public BasketEntity updateCarrot(@RequestBody UpdateCarrotDto req){
        UserEntity user = userService.getById(req.getUserId());
        return basketService.updateCarrot(user, req.getAddCarrot(), req.getTransferType());
    }

    @GetMapping("")
    public List<ShowCarrotDto> getBaskets(){
        return basketService.showAllUserBaskets();
    }

    @GetMapping("{userId}")
    public ShowCarrotDto getBasket(@PathVariable long userId){
        UserEntity user = userService.getById(userId);
        return basketService.showUserActiveBasket(user, true);
    }
}
