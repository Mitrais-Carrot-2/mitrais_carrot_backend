package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<TransferEntity, Integer> {
    List<TransferEntity> findBySenderIdAndType(Long senderId, ETransferType type);
}
