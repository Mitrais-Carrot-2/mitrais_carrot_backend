package com.team.two.mitrais_carrot.service.exchange;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.exchange.ExchangeEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.repository.BazaarItemRepository;
import com.team.two.mitrais_carrot.repository.ExchangeRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.basket.EBasket;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;
import com.team.two.mitrais_carrot.service.user.UserService;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ExchangeService {
    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private BazaarItemRepository bazaarItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BazaarItemService bazaarItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @Getter
    public enum ExchangeStatus{
        Success(200, "[SUCCESS] Purchasing item"),
        NotEnoughCarrots(400, "[FAILED] There are not enough carrots to buy this item"),
        ItemNotFound(404, "[FAILED] Item is not found / unavailable");

        int errorCode;
        String message;
        private ExchangeStatus(int errorCode, String message){
            this.errorCode = errorCode;
            this.message = message;
        }
    }

    private ExchangeStatus response;

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
        BasketEntity basket = basketService.getActiveBasket(buyer, true);
        return (basket.getCarrotAmount() >= item.getPrice());
    }

    public ExchangeStatus buyBazaarItem(long buyerId, int itemId){
        UserEntity buyer = userService.getById(buyerId);
        BazaarItemEntity item = bazaarItemService.getById(itemId);
        if (buyer != null){
            if (item == null) {
                response = ExchangeStatus.ItemNotFound;
            }
            else {
                if (isCarrotEnough(buyer, item)){
                    add(buyer, item);
                    bazaarItemService.updateQuantity(itemId, -1);
                    basketService.updateCarrot(buyer, -1*(item.getPrice()), EBasket.BAZAAR);
                    response = ExchangeStatus.Success;
                }
                else {
                    response = ExchangeStatus.NotEnoughCarrots;
                }
            }
        }

        return response;
    }
    

}