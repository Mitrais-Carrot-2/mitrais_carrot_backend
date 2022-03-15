package com.team.two.mitrais_carrot.service.basket;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.repository.BasketRepository;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BarnService barnService;

    public BasketEntity add(long userId){
        BasketEntity basket = new BasketEntity();

        basket.setUserId(userId);
        basket.setBarnId(barnService.isActiveBarn(true));
        basket.setShareCarrot(0L);
        basket.setRewardCarrot(0L);
        basket.setBazaarCarrot(0L);
        basket.setCarrotAmount(0L);

        return basketRepository.save(basket);
    }

    public List<BasketEntity> getAll() {
        return (List<BasketEntity>) basketRepository.findAll();
    }

    public BasketEntity updateCarrot(UserEntity user, long addCarrot, EBasket transferType){
        System.out.println("GET NEW ACTIVE BASKET");

        BasketEntity basket = getActiveBasket(user, true);

        System.out.println("basket = " + basket);

        if (transferType == EBasket.SHARE){
            addShareCarrot(basket, addCarrot);
        }
        else if (transferType == EBasket.REWARD) {
            addRewardCarrot(basket, addCarrot);
        }
        else if (transferType == EBasket.BAZAAR) {
            addBazaarCarrot(basket, addCarrot);
        }
        else {
            System.out.println("Transfer type is not defined!");
        }

        long totalCarrot = basket.getShareCarrot() + basket.getRewardCarrot() + basket.getBazaarCarrot();
        basket.setCarrotAmount(totalCarrot);

        return basketRepository.save(basket);
    }

    private void addShareCarrot(BasketEntity basket, long addCarrot){
        long newCarrot = basket.getCarrotAmount() + addCarrot;
        if (newCarrot < 0) newCarrot = 0;
        basket.setShareCarrot(newCarrot);
    }

    private void addRewardCarrot(BasketEntity basket, long addCarrot){
        long newCarrot = basket.getCarrotAmount() + addCarrot;
        if (newCarrot < 0) newCarrot = 0;
        basket.setRewardCarrot(newCarrot);
    }

    private void addBazaarCarrot(BasketEntity basket, long addCarrot){
        long newCarrot = basket.getCarrotAmount() + addCarrot;
        basket.setBazaarCarrot(newCarrot);
    }

    public BasketEntity getActiveBasket (UserEntity user, boolean isActive) {
        return basketRepository.findByUserIdAndBarnId(user.getId(), barnService.isActiveBarn(isActive).getBarnId());
    }

    public int getActiveBasketId (UserEntity user, boolean isActive) {
        return getActiveBasket(user, isActive).getBasketId();
    }

    public long getTotalCarrot(UserEntity user){
        BasketEntity activeBasket = getActiveBasket(user, true);
        return activeBasket.getCarrotAmount();
    }

    public long getSharedCarrot(UserEntity user){
        BasketEntity activeBasket = getActiveBasket(user, true);
        return activeBasket.getShareCarrot();
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
