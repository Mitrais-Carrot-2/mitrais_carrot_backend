package com.team.two.mitrais_carrot.repository.admin;

import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.entity.admin.ETypeBarnReward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarnRewardRepository extends JpaRepository <BarnRewardEntity, Integer> {

    BarnRewardEntity findByGivingConditional(ETypeBarnReward type);

    // BarnRewardEntity findByGivingConditionalEqual(ETypeBarnReward type);

    // BarnRewardEntity findByGivingConitionalIsEqual(ETypeBarnReward.USER_BIRTHDAY);
    
}
