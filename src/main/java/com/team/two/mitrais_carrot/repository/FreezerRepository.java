package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreezerRepository extends JpaRepository<FreezerEntity, Integer> {

    FreezerEntity findByManagerIdAndBarn_Id(Long managerId, Integer barnId);

    FreezerEntity findByBarn_Id(Integer barnId);

    FreezerEntity findByBarn_IdAndManagerId(Integer barnId, Long managerId);

}
