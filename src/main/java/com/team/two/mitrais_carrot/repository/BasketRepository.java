package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
//    BasketEntity findByUserIdAndBarnId(long userId, int barnId);

    BasketEntity findByUserIdAndBarnId_BarnId(long userId, Integer barnId);

    BasketEntity findByUserId_IdAndBarnId_BarnId(Long userId, Integer barnId);


}
