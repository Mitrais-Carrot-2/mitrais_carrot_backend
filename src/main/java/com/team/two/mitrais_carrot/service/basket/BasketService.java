package com.team.two.mitrais_carrot.service.basket;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
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

    public BasketEntity getActiveBasket (UserEntity user, boolean isActive) {
        return basketRepository.findByUserIdAndBarnId(user.getId(), barnService.isActiveBarn(isActive));
    }

    public int getActiveBasketId (UserEntity user, boolean isActive) {
        return getActiveBasket(user, isActive).getId();
    }

    public long getTotalCarrot(UserEntity user){
        BasketEntity activeBasket = getActiveBasket(user, true);
        return activeBasket.getCarrotAmount();
    }

    public long getSharedCarrot(UserEntity user){
        BasketEntity activeBasket = getActiveBasket(user, true);
        return activeBasket.getSharedCarrot();
    }

    public long getRewardCarrot(UserEntity user){
        BasketEntity activeBasket = getActiveBasket(user, true);
        return activeBasket.getRewardCarrot();
    }

    public long getBazaarCarrot(UserEntity user){
        BasketEntity activeBasket = getActiveBasket(user, true);
        return activeBasket.getBazaarCarrot();
    }
}
