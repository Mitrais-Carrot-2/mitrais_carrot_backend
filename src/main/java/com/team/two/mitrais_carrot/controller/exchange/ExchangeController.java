package com.team.two.mitrais_carrot.controller.exchange;

import com.team.two.mitrais_carrot.dto.exchange.ExchangeDataDto;
import com.team.two.mitrais_carrot.entity.exchange.EExchangeStatus;
import com.team.two.mitrais_carrot.entity.exchange.ExchangeEntity;
import com.team.two.mitrais_carrot.service.exchange.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {
    @Autowired
    ExchangeService exchangeService;

    @PostMapping("")
    public ExchangeService.ExchangeStatus buyItem(@RequestParam(value = "userId") long userId, @RequestParam(value = "itemId") int itemId){
        return exchangeService.buyBazaarItem(userId, itemId);
    }

//    @GetMapping("")
//    public List<ExchangeEntity> getAllExchanges(){
//        return exchangeService.getAll();
//    }

    @GetMapping("")
    public List<ExchangeDataDto> getAllExchanges(){
        return exchangeService.getAllExchange();
    }


    @PutMapping("")
    public ResponseEntity<?> setExchangeStatus(@RequestParam(value = "exchangeId") long exchangeId, @RequestParam(value = "status") EExchangeStatus status){
        ExchangeEntity exchange = exchangeService.getById(exchangeId);
        return exchangeService.setExchangeStatus(exchange, status);

    }
}
