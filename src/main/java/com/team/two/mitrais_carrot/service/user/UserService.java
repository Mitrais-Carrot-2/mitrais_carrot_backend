package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.dto.auth.UserDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import com.team.two.mitrais_carrot.repository.BazaarItemRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.service.merchant.BazaarItemService;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    
    private int status;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public UserEntity addUser(UserDto user){

    }

    public UserEntity fetchById(int id){
        return userRepository.findById(id).orElse(null);
    }
    
}