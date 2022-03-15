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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.List;



@Service
public class UserService {
    private final UserRepository userRepository;

    private BasketService basketService;

    @Autowired
    PasswordEncoder encoder;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

     public UserEntity add(UserDto req){
        UserEntity user = new UserEntity(req.getUsername(), req.getPassword(), req.getEmail());
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