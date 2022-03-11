package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BazaarItemRepository extends JpaRepository<BazaarItemEntity, Integer>{

}