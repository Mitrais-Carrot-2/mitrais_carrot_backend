package com.team.two.mitrais_carrot.service.exchange;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.exchange.ExchangeEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.repository.BazaarItemRepository;
import com.team.two.mitrais_carrot.repository.ExchangeRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;
import com.team.two.mitrais_carrot.service.user.UserService;


import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ExchangeService {
    private ExchangeRepository exchangeRepository;
    private BazaarItemRepository bazaarItemRepository;
    private UserRepository userRepository;

    private BazaarItemService bazaarItemService;
    private UserService userService;

    @Getter
    enum status{
        Success(200, "[SUCCESS] Purchasing item"),
        NotEnoughCarrots(400, "[FAILED] There are not enough carrots to buy this item"),
        ItemNotFound(404, "[FAILED] Item is not found / unavailable");

        int errorCode;
        String message;
        private status(int errorCode, String message){
            this.errorCode = errorCode;
            this.message = message;
        }
    }

    private status response;

    public ExchangeService(ExchangeRepository exchangeRepository) {this.exchangeRepository = exchangeRepository;}

    public ExchangeEntity add(UserEntity buyer, BazaarItemEntity item){
        ExchangeEntity exchange = new ExchangeEntity();
        exchange.setStatus(true);
        exchange.setUserId(buyer.getId());
        exchange.setPrice(item.getPrice());
        exchange.setBazaarItemId(item.getId());
        exchange.setExchangeDate(LocalDate.now());

        return exchangeRepository.save(exchange);
    }

    public boolean isCarrotEnough(UserEntity buyer, BazaarItemEntity item){
        //using stream get the user's basket carrotAmount
        //TODO LIST STREAM GET ALL CARROT AMOUNT
//        BasketEntity basket = buyer.getBaskets().stream().findFirst().get();
//
//        return (basket.getCarrotAmount() >= item.getPrice());
        return true;
    }

    public status buyBazaarItem(int buyerId, int itemId){
        UserEntity buyer = userService.fetchById(buyerId);
        BazaarItemEntity item = bazaarItemService.fetchById(itemId);
        if (buyer != null){
            if (item == null) {
                response = status.ItemNotFound;
            }
            else {
                if (isCarrotEnough(buyer, item)){
                    add(buyer, item);
                    bazaarItemService.updateQuantity(itemId, -1);
                    userService.updateCarrot(buyerId, -(item.getPrice()));
                    response = status.Success;
                }
                else {
                    response = status.NotEnoughCarrots;
                }
            }
        }

        return response;
    }
    

}