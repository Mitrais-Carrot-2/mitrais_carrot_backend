package com.team.two.mitrais_carrot.service.admin;

import java.util.List;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.admin.BarnRewardDto;
import com.team.two.mitrais_carrot.dto.admin.EditBarnRewardDto;
import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.entity.admin.ETypeBarnReward;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.repository.admin.BarnRewardRepository;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import com.team.two.mitrais_carrot.service.transfer.TransferService;
import com.team.two.mitrais_carrot.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BarnRewardService {
    private final BarnRewardRepository barnRewardRepository;
    private final BarnRepository barnRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private BarnService barnService;


    public BarnRewardService(BarnRewardRepository barnRewardRepository, BarnRepository barnRepository) {
        this.barnRewardRepository = barnRewardRepository;
        this.barnRepository = barnRepository;
    }

    public List<BarnRewardEntity> fetchAllBarnRewards() {
        return barnRewardRepository.findAll();
    }

    public BarnRewardEntity findById(int id) {
        return barnRewardRepository.findById(id).orElse(null);
    }

    public BarnRewardEntity searchBarnRewardByType(ETypeBarnReward type) {
        return barnRewardRepository.findByGivingConditional(type);
    }

    public BarnRewardEntity searchBarnRewardByTypeAndeBarn(ETypeBarnReward type, BarnEntity barn) {
        return barnRewardRepository.findByGivingConditionalAndBarn(type, barn);
    }

    public BarnRewardEntity searchBarnRewardByDescription(String description) {
        return barnRewardRepository.findByRewardDescription(description);
    }

    public List<BarnRewardEntity> fetchBarnRewardById(int id) {
        return barnRewardRepository.findByBarnId(id);
    }

    public ResponseEntity<?> createBarnReward(BarnRewardDto req) {
        BarnRewardEntity barnReward = new BarnRewardEntity();
        BarnEntity barn = barnRepository.findById(req.getBarnId()).orElse(null);

        barnReward.setRewardDescription(req.getRewardDescription());
        barnReward.setCarrotAmount(req.getCarrotAmount());
        barnReward.setGivingConditional(req.getGivingConditional());
        barnReward.setBarn(barn);
        barnRewardRepository.save(barnReward);
        barn.getBarnReward().add(barnReward);
        barnRepository.save(barn);
        return ResponseEntity
                .ok(new MessageDto(barnReward, String.format("Successfully created reward: %s", req.getRewardDescription()), true));

    }

    public BarnRewardEntity editBarnRewardEntity(int id, EditBarnRewardDto req) {
        BarnRewardEntity selectedBarnReward = this.findById(id);
        selectedBarnReward.setRewardDescription(req.getRewardDescription());
        selectedBarnReward.setCarrotAmount(req.getCarrotAmount());
        selectedBarnReward.setGivingConditional(req.getGivingConditional());
        return barnRewardRepository.save(selectedBarnReward);
    }

    public List<UserEntity> rewardByBirthDay() {

        BarnEntity activeBarn = barnService.isActiveBarn(true);

        long amount = this.searchBarnRewardByTypeAndeBarn(ETypeBarnReward.USER_BIRTHDAY, activeBarn).getCarrotAmount();

        List<UserEntity> birthdayPerson = userService.getBirthdayPerson();
        for (UserEntity user : birthdayPerson) {
            transferService.transferBarnReward(user, amount, ETransferType.TYPE_REWARD);
        }

        return birthdayPerson;
    }

    public ResponseEntity<?> deleteBarnReward(int id) {
        BarnRewardEntity selectedBarnReward = this.findById(id);
        barnRewardRepository.delete(selectedBarnReward);
        return ResponseEntity
                .ok(new MessageDto(String.format("Successfully deleted reward: %s", selectedBarnReward.getRewardDescription()), true));
    }

    public ETypeBarnReward[] fetchAllRewardType() {
        return ETypeBarnReward.values();
    }

}
