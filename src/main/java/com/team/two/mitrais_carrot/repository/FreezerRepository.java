package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.exchange.ExchangeEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreezerRepository extends JpaRepository<FreezerEntity, Integer> {
    FreezerEntity findByManagerIdEqualsAndBarnId_BarnIdEquals(Long managerId, Integer barnId);

    FreezerEntity findByBarnId_FreezerId_BarnIdAndBarnId_FreezerId_ManagerId(BarnEntity barnId, Long managerId);
}
