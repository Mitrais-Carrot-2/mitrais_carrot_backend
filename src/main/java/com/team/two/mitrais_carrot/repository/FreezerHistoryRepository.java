package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreezerHistoryRepository extends JpaRepository<FreezerHistoryEntity, Integer> {

}
