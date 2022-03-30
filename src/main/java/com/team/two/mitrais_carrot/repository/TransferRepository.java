package com.team.two.mitrais_carrot.repository;

import java.util.List;

import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferEntity, Integer> {

    List<TransferEntity> findBySenderIdAndType(Long barnId, ETransferType typeBarnToFreezer);
}
