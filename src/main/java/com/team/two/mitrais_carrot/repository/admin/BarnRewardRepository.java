package com.team.two.mitrais_carrot.repository.admin;

import java.util.List;

import com.team.two.mitrais_carrot.entity.admin.BarnRewardEntity;
import com.team.two.mitrais_carrot.entity.admin.ETypeBarnReward;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarnRewardRepository extends JpaRepository<BarnRewardEntity, Integer> {

    BarnRewardEntity findByGivingConditional(ETypeBarnReward type);

    BarnRewardEntity findByRewardDescription(String description);

    boolean existsByRewardDescription(String rewardDescription);

    List<BarnRewardEntity> findByBarnId(int id);

    BarnRewardEntity findByGivingConditionalAndBarn(ETypeBarnReward type, BarnEntity barn);

    // BarnRewardEntity findByGivingConditionalEqual(ETypeBarnReward type);

    // BarnRewardEntity
    // findByGivingConitionalIsEqual(ETypeBarnReward.USER_BIRTHDAY);

}
