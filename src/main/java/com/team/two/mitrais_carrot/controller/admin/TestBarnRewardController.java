package com.team.two.mitrais_carrot.controller.admin;

import java.util.List;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.service.admin.BarnRewardService;
import com.team.two.mitrais_carrot.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/test_barn_reward")
public class TestBarnRewardController {
    @Autowired
    BarnRewardService barnRewardService;
    // @Autowired
    // UserService userService;

    @GetMapping("/")
    public List<UserEntity> testBirthday(){
        return barnRewardService.rewardByBirthDay(); //cannot call this function
        // return userService.getBirthdayPerson();
    }
    
    
}
