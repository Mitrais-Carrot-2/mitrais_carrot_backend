package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.dto.UpdatePasswordDto;
import com.team.two.mitrais_carrot.dto.UpdateProfileDto;
import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.repository.BasketRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.basket.EBasket;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;

import lombok.extern.java.Log;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import logger
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.Lob;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


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

        BarnEntity barnId = barnService.isActiveBarn(true);
        System.out.println("BARN ID = " + barnId);

        BasketEntity basket = basketService.add(user.getId());
        user.setBaskets(basket);
        System.out.println("SET BASKET" + basket);

        return userRepository.save(user);
     }
    Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

    public List<UserEntity> getAll(){ return userRepository.findAll(); }

    public UserEntity getById(long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> getBirthdayPerson(){

        // return this.getAll().stream().filter(person -> {return person.getDayOfYearBirthDay() == LocalDate.now().getDayOfYear();}).collect(Collectors.toList());
        
        return userRepository.findAllByDayOfYearBirthDay(LocalDate.now().getDayOfYear());
    }


    //findByUsername
    public UserEntity getByUsername(String username){
            long userId = userRepository.findByUsername(username).getId();
            logger.info("User ID: " + userId);
            if(userId != 0){
                return getById(userId);
            }
            return null;
    }

    public Boolean checkPassword(String username, String password){
        long userId = userRepository.findByUsername(username).getId();
        if(userId != 0){
            UserEntity user = getById(userId);
            if(encoder.matches(password, user.getPassword()) == true){
                logger.info("" + user.getUsername() + " Password is correct");
                return true;
            }
            else {
                logger.info("" + user.getUsername() + " Password is incorrect");
                return false;
            }

        }
        return false;
    }

    public UserEntity changePassword(String username, String newPassword) {
        UserEntity user = getByUsername(username);
        user.setPassword(encoder.encode(newPassword));
        return userRepository.save(user);
    }

    public UserEntity updateProfile(String username,UpdateProfileDto req ) {
        UserEntity user = getByUsername(username);
        user.setUsername(req.getUsername());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setAddress(req.getAddress());
        user.setEmail(req.getEmail());

        return userRepository.save(user);
        }

    public void changeImage(String username, Lob image) {
        UserEntity user = getByUsername(username);
    }

}