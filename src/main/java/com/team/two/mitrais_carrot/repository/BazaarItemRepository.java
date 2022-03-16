package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.merchant.BazaarItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BazaarItemRepository extends JpaRepository<BazaarItemEntity, Integer>{
    List<BazaarItemEntity> findByBazaar_Id(Integer id);
//    List<BazaarItemEntity> findByBazaar_id_Id(Integer id);

    BazaarItemEntity findOneById(Integer integer);

}