package com.team.two.mitrais_carrot.repository.farmer;

import com.team.two.mitrais_carrot.entity.farmer.BarnRewardEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarnRewardRepository extends JpaRepository <BarnRewardEntity, Integer> {
    
}
