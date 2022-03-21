package com.team.two.mitrais_carrot.service.admin;

import java.util.List;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.admin.BarnRewardDto;
import com.team.two.mitrais_carrot.dto.admin.EditBarnRewardDto;
import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.entity.admin.ETypeBarnReward;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.repository.admin.BarnRewardRepository;
import com.team.two.mitrais_carrot.service.transfer.TransferService;
import com.team.two.mitrais_carrot.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    // return barnRewardRepository.findByGivingConditionalEqual(type);
    // }

    public List<BarnRewardEntity> fetchAllBarnRewards() {
        return barnRewardRepository.findAll();
    }

    public BarnRewardEntity searchBarnRewardByType(ETypeBarnReward type) {
        return barnRewardRepository.findByGivingConditional(type);
    }

    public BarnRewardEntity searchBarnRewardByDescription(String description) {
        return barnRewardRepository.findByRewardDescription(description);
    }

    public ResponseEntity<?> createBarnReward(BarnRewardDto req) {
        if (barnRewardRepository.existsByRewardDescription(req.getRewardDescription())) {
            return ResponseEntity.badRequest().body(new MessageDto(
                    String.format("%s already exist. Please use change instead", req.getRewardDescription()), false));
        }
        BarnRewardEntity barnReward = new BarnRewardEntity();
        barnReward.setRewardDescription(req.getRewardDescription());
        barnReward.setCarrotAmount(req.getCarrotAmount());
        barnReward.setGivingConditional(req.getGivingConditional());
        barnRewardRepository.save(barnReward);
        return ResponseEntity
                .ok(new MessageDto(String.format("Successfully created reward: %s", req.getRewardDescription()), true));

    }

    public BarnRewardEntity editBarnRewardEntity(EditBarnRewardDto req) {
        BarnRewardEntity selectedBarnReward = this.searchBarnRewardByDescription(req.getRewardDescription());
        selectedBarnReward.setCarrotAmount(req.getCarrotAmount());
        return barnRewardRepository.save(selectedBarnReward);
    }

    public List<UserEntity> rewardByBirthDay() {

        long amount = this.searchBarnRewardByType(ETypeBarnReward.USER_BIRTHDAY).getCarrotAmount();

        List<UserEntity> birthdayPerson = userService.getBirthdayPerson();
        for (UserEntity user : birthdayPerson) {
            transferService.transferBarnReward(user, amount, ETransferType.TYPE_REWARD);
        }

        return birthdayPerson;
    }

}
