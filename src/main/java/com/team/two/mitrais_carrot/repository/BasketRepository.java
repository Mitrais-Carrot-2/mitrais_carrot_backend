package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
    BasketEntity findByUserIdAndBarnId(long userId, int barnId);

<<<<<<< HEAD
    BasketEntity findByUserIdAndBarnId_BarnId(Long id, Integer barnId);
=======
    BasketEntity findByUserIdAndBarnId_BarnId(long userId, Integer barnId);


//    BasketEntity findByBarnId_IdUserAndUserId(Integer idBarn, long userId);
>>>>>>> 08ccb27e8ce84bc8bc490a5ae378a4b51e183ad9
}
