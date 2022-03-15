package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.repository.BasketRepository;
import com.team.two.mitrais_carrot.repository.BazaarItemRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.basket.EBasket;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.List;



@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketService basketService;

    @Autowired
    private BarnService barnService;

    @Autowired
    PasswordEncoder encoder;


     public UserEntity add(UserDto req){
        UserEntity user = new UserEntity(req.getUsername(), req.getPassword(), req.getEmail());
        userRepository.save(user);
        System.out.println("FINISHED SAVING USER");

        int barnId = barnService.isActiveBarn(true);
        System.out.println("BARN ID = " + barnId);

        BasketEntity basket = basketService.add(user.getId());
        user.setBaskets(basket);
        System.out.println("SET BASKET" + basket);

        return userRepository.save(user);
     }

    public List<UserEntity> getAll(){ return userRepository.findAll(); }

    public UserEntity getById(long id){ return userRepository.findById(id).orElse(null);}

    public UserEntity getByUsername(String username){ return userRepository.findByUsername(username).orElse(null); }
    //public change password function
    public Boolean changePassword(String username, String oldPassword, String newPassword){
        UserEntity user = getByUsername(username);
        if (encoder.matches(oldPassword, user.getPassword())){
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        //return password is false or username is not found
        return false;
    }

}