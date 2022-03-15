package com.team.two.mitrais_carrot.controller.user;

import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
//    @Autowired
    UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("")
    public UserEntity addUser(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }

    @GetMapping("/")
    public List<UserEntity> getAllUsers(){
        return userService.getAll();
    }

//    @GetMapping("{id}")
//    public UserEntity getUser(@PathVariable("id") String id){
//        return userService.getById(Long.parseLong(id));
//    }

}
