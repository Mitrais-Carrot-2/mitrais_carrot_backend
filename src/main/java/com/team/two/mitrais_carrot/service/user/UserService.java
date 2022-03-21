package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.dto.UpdateProfileDto;
import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasketService basketService;

    @Autowired
    PasswordEncoder encoder;

    Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

    public UserEntity add(UserDto req) {
        UserEntity user = new UserEntity(req.getUsername(), req.getPassword(), req.getEmail());

        this.calculateDayOfYearBirthday(user);

        BasketEntity basket = basketService.add(user);
        user.getBaskets().add(basket);

        return userRepository.save(user);
    }

    public UserEntity add(UserEntity user) {
        this.calculateDayOfYearBirthday(user);

        BasketEntity basket = basketService.add(user);
        user.getBaskets().add(basket);

        return userRepository.save(user);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> getBirthdayPerson() {
        LocalDate today = LocalDate.now();
        if (!(today.isLeapYear()) && (today.getDayOfYear() > 60)) {
            return userRepository.findAllByDayOfYearBirthDay(LocalDate.now().getDayOfYear() + 1);
        } else if (!(today.isLeapYear()) && (today.getDayOfYear() == 60)) { // For Mar 1 in not leap year; give to both
                                                                            // birthday on 29 Feb & 1 Mar
            List<UserEntity> cascade = userRepository.findAllByDayOfYearBirthDay(LocalDate.now().getDayOfYear() + 1);
            cascade.addAll(userRepository.findAllByDayOfYearBirthDay(LocalDate.now().getDayOfYear()));
            return cascade;
        } else {
            return userRepository.findAllByDayOfYearBirthDay(LocalDate.now().getDayOfYear());
        }

    }

    // findByUsername
    public UserEntity getByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            logger.info("User ID: " + userEntity.getId());
            return userEntity;
        }
        return null;
    }

    public Boolean checkPassword(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            if (encoder.matches(password, userEntity.getPassword()) == true) {
                logger.info("" + userEntity.getUsername() + " Password is correct");
                return true;
            } else {
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

    public UserEntity updateProfile(String username, UpdateProfileDto req) {
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

    public UserEntity calculateDayOfYearBirthday(UserEntity user) {
        if (!user.getBirthDate().isLeapYear() && (user.getBirthDate().getDayOfYear() >= 60)) { // standarisasi day 60 =
                                                                                               // 29 Feb, day 61 = 1 Mar
            user.setDayOfYearBirthDay(user.getBirthDate().getDayOfYear() + 1);
        } else {
            user.setDayOfYearBirthDay(user.getBirthDate().getDayOfYear());
        }
        return userRepository.save(user);
    }
}