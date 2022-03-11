package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.merchant.BazaarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BazaarRepository extends JpaRepository<BazaarEntity, Integer>{

}