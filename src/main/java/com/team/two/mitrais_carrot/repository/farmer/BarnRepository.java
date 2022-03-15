package com.team.two.mitrais_carrot.repository.farmer;

import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarnRepository extends JpaRepository <BarnEntity, Integer> {
    BarnEntity findByIsActive(Boolean isActive);

    List<BarnEntity> findByIsActiveFalse();

    List<BarnEntity> findByIsActiveTrue();


}