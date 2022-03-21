package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.award.AwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRepository extends JpaRepository<AwardEntity, Integer> {
}
