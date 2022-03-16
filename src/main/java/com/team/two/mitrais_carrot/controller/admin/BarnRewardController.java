package com.team.two.mitrais_carrot.controller.admin;

import java.util.List;

import com.team.two.mitrais_carrot.dto.admin.BarnRewardDto;
import com.team.two.mitrais_carrot.dto.admin.EditBarnRewardDto;
import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.service.admin.BarnRewardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// TODO : Buat authentikasi agar admin access only
@RestController
@RequestMapping("/api/admin/barn_reward")
public class BarnRewardController {
    @Autowired
    BarnRewardService barnRewardService;

    @GetMapping("/")
    public List<BarnRewardEntity> fetchBarnReward(){
        return barnRewardService.fetchAllBarnRewards();
    }

    @PostMapping("/")
    public BarnRewardEntity postBarnReward(@RequestBody BarnRewardDto request){
        return barnRewardService.createBarnReward(request);
    }

    @PutMapping("/")
    public BarnRewardEntity editBarnReward(@RequestBody EditBarnRewardDto request){
        return barnRewardService.editBarnRewardEntity(request);
    }
    
}
