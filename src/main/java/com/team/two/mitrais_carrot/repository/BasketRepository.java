package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
    BasketEntity findByUser_IdAndBarn_Id(Long id, Integer id1);

}
