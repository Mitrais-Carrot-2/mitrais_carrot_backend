package com.team.two.mitrais_carrot.service.admin;

import java.util.List;

import javax.validation.constraints.Null;

import com.team.two.mitrais_carrot.dto.admin.BarnRewardDto;
import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.entity.admin.ETypeBarnReward;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.repository.admin.BarnRewardRepository;
import com.team.two.mitrais_carrot.service.transfer.TransferService;
import com.team.two.mitrais_carrot.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarnRewardService {
    private final BarnRewardRepository barnRewardRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TransferService transferService;

    public BarnRewardService(BarnRewardRepository barnRewardRepository) {
        this.barnRewardRepository = barnRewardRepository;
    }
    // public BarnRewardEntity getBarnRewardByType(ETypeBarnReward type){
    //     return barnRewardRepository.findByGivingConditionalEqual(type);
    // }

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

    public List<UserEntity> rewardByBirthDay(){
        
        List<UserEntity> birthdayPerson =  userService.getBirthdayPerson();
        for (UserEntity user : birthdayPerson){
            transferService.transferBarnReward(user, 10L, ETransferType.TYPE_REWARD);
        }
        
        return birthdayPerson;
    }

    //TODO : Give reward as per date
}
