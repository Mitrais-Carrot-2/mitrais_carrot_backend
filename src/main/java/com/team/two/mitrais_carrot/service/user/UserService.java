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
import javax.persistence.Transient;
import java.awt.*;
import java.time.LocalDate;
import java.io.IOException;
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

        user.setBirthDate(LocalDate.now());

        user.setDayOfYearBirthDay(user.getBirthDate().getDayOfYear());

        userRepository.save(user);
        System.out.println("FINISHED SAVING USER");

        BarnEntity barnId = barnService.isActiveBarn(true);
        System.out.println("BARN ID = " + barnId.getBarnId());

        BasketEntity basket = basketService.add(user.getId());
        user.getBaskets().add(basket);
        System.out.println("SET BASKET" + basket);

        return userRepository.save(user);
     }
    Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

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
            UserEntity userEntity = userRepository.findByUsername(username);
            if(userEntity != null){
                logger.info("User ID: " + userEntity.getId());
                return userEntity;
            }
            return null;
    }

    public Boolean checkPassword(String username, String password){
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
            if(encoder.matches(password, userEntity.getPassword()) == true){
                logger.info("" + userEntity.getUsername() + " Password is correct");
                return true;
            }
            else {
                logger.info("" + userEntity.getUsername() + " Password is incorrect");
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
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setAddress(req.getAddress());
        user.setEmail(req.getEmail());

        return userRepository.save(user);
        }

        public void saveImage(String username, MultipartFile file) throws IOException {
            UserEntity user = getByUsername(username);
            user.setImageType(file.getContentType());
            user.setImage(file.getBytes());
            user.setImageSize(file.getSize());
            userRepository.save(user);
        }
}