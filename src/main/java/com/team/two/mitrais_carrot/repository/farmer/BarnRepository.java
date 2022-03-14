package com.team.two.mitrais_carrot.repository.farmer;

import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarnRepository extends JpaRepository <BarnEntity, Integer> {
    BarnEntity findBy();

    BarnEntity findByIsActive(Boolean isActive);
}