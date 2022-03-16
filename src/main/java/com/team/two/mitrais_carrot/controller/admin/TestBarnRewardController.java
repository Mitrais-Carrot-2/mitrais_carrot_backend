package com.team.two.mitrais_carrot.controller.admin;

import java.util.List;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.service.admin.BarnRewardService;
import com.team.two.mitrais_carrot.service.transfer.TransferService;
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
    @Autowired
    UserService userService;
    @Autowired
    TransferService transferService;

    @GetMapping("/")
    public List<UserEntity> testBirthday(){
        // return barnRewardService.rewardByBirthDay();
        return userService.getBirthdayPerson();
    }

    @GetMapping("TransferOnly/")
    public List<UserEntity> testTransfer(){
        // UserEntity birthdayPerson = userService.getBirthdayPerson().stream().findFirst().get();
        // // return birthdayPerson;
        // return transferService.transferBarnReward(birthdayPerson, 10L, ETransferType.TYPE_REWARD);

        return barnRewardService.rewardByBirthDay();
    }
    
    
}
