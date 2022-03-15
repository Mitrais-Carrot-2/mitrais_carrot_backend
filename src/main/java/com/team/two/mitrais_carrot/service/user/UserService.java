package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.repository.BasketRepository;
import com.team.two.mitrais_carrot.repository.BazaarItemRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    private BasketService basketService;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

     public UserEntity add(UserDto req){
        UserEntity user = new UserEntity(req.getUsername(), req.getPassword(), req.getEmail());
        return userRepository.save(user);
     }

    public List<UserEntity> getAll(){ return (List<UserEntity>) userRepository.findAll(); }

    public UserEntity getById(long id){ return userRepository.findById(id).orElse(null);}

    public UserEntity updateCarrot(int userId, int addCarrot){
        UserEntity user = getById(userId);

        BasketEntity basket = basketService.getActiveBasket(user, true);

        long newCarrot = basket.getCarrotAmount() + addCarrot;
        if (newCarrot < 0) newCarrot = 0;
        basket.setCarrotAmount(newCarrot);

        return userRepository.save(user);
    }

}