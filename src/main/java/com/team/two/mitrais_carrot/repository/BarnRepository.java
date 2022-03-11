package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarnRepository extends JpaRepository <BarnEntity, Long > {
   
}