package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.repository.BazaarItemRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

     public UserEntity add(UserDto req){
        UserEntity user = new UserEntity(req.getUsername(), req.getPassword(), req.getEmail());
        return userRepository.save(user);
     }

    public List<UserEntity> fetchAll(){ return (List<UserEntity>) userRepository.findAll(); }

    public UserEntity fetchById(long id){ return userRepository.findById(id).orElse(null);}

    public UserEntity updateCarrot(int userId, int addCarrot){
        UserEntity user = fetchById(userId);
        //TODO LIST STREAM GET ALL CARROT AMOUNT
        BasketEntity basket = user.getBaskets().stream().findFirst().get();
        if (basket.isActive()){
            long newCarrot = basket.getCarrotAmount() + addCarrot;
            if (newCarrot < 0) newCarrot = 0;
            basket.setCarrotAmount(newCarrot);
        }
        else System.out.println("Basket Is Inactive");

        return userRepository.save(user);
    }

}