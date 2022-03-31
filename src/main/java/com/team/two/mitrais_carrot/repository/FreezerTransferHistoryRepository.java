package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.transfer.FreezerTransferHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreezerTransferHistoryRepository extends JpaRepository<FreezerTransferHistoryEntity, Integer> {
    List<FreezerTransferHistoryEntity> findByFreezer_Id(Integer id);
}
