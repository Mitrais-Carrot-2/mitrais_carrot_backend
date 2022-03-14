package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.transferHistory.TransferHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferHistoryRepository extends JpaRepository<TransferHistoryEntity, Integer> {
}
