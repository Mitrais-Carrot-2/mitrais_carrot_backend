package com.team.two.mitrais_carrot.service.admin;

import java.util.List;

import com.team.two.mitrais_carrot.dto.admin.BarnRewardDto;
import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.repository.admin.BarnRewardRepository;

import org.springframework.stereotype.Service;

@Service
public class BarnRewardService {
    private final BarnRewardRepository barnRewardRepository;

    public BarnRewardService(BarnRewardRepository barnRewardRepository) {
        this.barnRewardRepository = barnRewardRepository;
    }

    public List<BarnRewardEntity> fetchAllBarnRewards(){
        return barnRewardRepository.findAll();
    }

    public BarnRewardEntity createBarnReward(BarnRewardDto req) {
        BarnRewardEntity barnReward = new BarnRewardEntity();
        barnReward.setRewardDescription(req.getRewardDescription());
        barnReward.setCarrotAmount(req.getCarrotAmount());
        barnReward.setGivingConditional(req.getGivingConditional());
        return barnRewardRepository.save(barnReward);

    }

    //TODO : Give reward as per date
}
