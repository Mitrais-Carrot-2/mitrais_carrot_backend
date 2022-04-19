package com.team.two.mitrais_carrot.service.basket;

import com.team.two.mitrais_carrot.dto.basket.ShowCarrotDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.basket.EBasket;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.repository.BasketRepository;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BarnService barnService;

    @Autowired
    BarnRepository barnRepository;

    public BasketEntity add(UserEntity user){
        BasketEntity basket = new BasketEntity();
        basket.setUser(user);
        basket.setBarn(barnService.isActiveBarn(true));
        basket.setShareCarrot(0L);
        basket.setRewardCarrot(0L);
        basket.setBazaarCarrot(0L);
        basket.setCarrotAmount(0L);

        return basketRepository.save(basket);
    }

    public List<BasketEntity> getAllBaskets() {
        return (List<BasketEntity>) basketRepository.findAll();
    }

    public List<ShowCarrotDto> showAllUserBaskets() {
        List<ShowCarrotDto> listCarrotDto = new ArrayList<>();
        List<BasketEntity> baskets = (List<BasketEntity>) basketRepository.findAll();
        baskets.stream()
                .forEach(b -> {
                    ShowCarrotDto carrotDto = new ShowCarrotDto(b.getUser().getId(), b);
                    listCarrotDto.add(carrotDto);
                });

        return listCarrotDto;
    }

    public BasketEntity updateCarrot(UserEntity user, long addCarrot, EBasket transferType){
        BasketEntity basket = getActiveBasket(user, true);


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
            // TODO : Ganti ke logger
            // System.out.println("Transfer type is not defined!");
        }

        long totalCarrot = basket.getShareCarrot() + basket.getRewardCarrot() + basket.getBazaarCarrot();
        basket.setCarrotAmount(totalCarrot);

        return basketRepository.save(basket);
    }

    private void addShareCarrot(BasketEntity basket, long addCarrot){
        long newCarrot = basket.getShareCarrot() + addCarrot;
        if (newCarrot < 0) newCarrot = 0;
        basket.setShareCarrot(newCarrot);
    }

    private void addRewardCarrot(BasketEntity basket, long addCarrot){
        long newCarrot = basket.getRewardCarrot() + addCarrot;
        if (newCarrot < 0) newCarrot = 0;
        basket.setRewardCarrot(newCarrot);
    }

    private void addBazaarCarrot(BasketEntity basket, long addCarrot){
        long newCarrot = basket.getBazaarCarrot() + addCarrot;
        basket.setBazaarCarrot(newCarrot);
    }

    public BasketEntity getActiveBasket(UserEntity user, boolean isActive) {
        return basketRepository.findByUser_IdAndBarn_Id(user.getId(), barnService.isActiveBarn(isActive).getId());
    }

    public ShowCarrotDto showUserActiveBasket(UserEntity user, boolean isActive){
        BasketEntity basket = getActiveBasket(user, isActive);

        if (basket == null) {
            basket = add(user);
        }

        return new ShowCarrotDto(user.getId(), basket);
    }

    public BasketEntity getActiveBasket (long userId, boolean isActive) {
        BarnEntity barn = barnService.isActiveBarn(isActive);
        return basketRepository.findByUser_IdAndBarn_Id(userId, barn.getId());
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
