package com.team.two.mitrais_carrot.service.farmer;

import com.team.two.mitrais_carrot.dto.farmer.BarnRewardDto;
import com.team.two.mitrais_carrot.entity.farmer.BarnRewardEntity;
import com.team.two.mitrais_carrot.repository.farmer.BarnRewardRepository;

import org.springframework.stereotype.Service;

@Service
public class BarnRewardService {
    private final BarnRewardRepository barnRewardRepository;

    public BarnRewardService(BarnRewardRepository barnRewardRepository) {
        this.barnRewardRepository = barnRewardRepository;
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
