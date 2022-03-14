package com.team.two.mitrais_carrot.service.basket;

import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.repository.BasketRepository;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    private BasketRepository basketRepository;
    private BarnService barnService;

    public BasketService(BasketRepository basketRepository) { this.basketRepository = basketRepository;}

    public List<BasketEntity> getAll() {
        return (List<BasketEntity>) basketRepository.findAll();
    }

    public BasketEntity getActiveBasket (boolean isActive) {
        return basketRepository.findByBarnId(barnService.getActiveBarnId(isActive));
    }

    public int getActiveBasketId (boolean isActive) {
        return getActiveBasket(isActive).getId();
    }
}
