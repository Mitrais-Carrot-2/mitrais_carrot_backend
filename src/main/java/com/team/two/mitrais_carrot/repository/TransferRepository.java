package com.team.two.mitrais_carrot.repository;


import java.util.List;

import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<TransferEntity, Integer> {


//     List<TransferEntity> findBySenderIdAndType(Long barnId, ETransferType typeBarnToFreezer);

    List<TransferEntity> findBySenderIdAndType(Long senderId, ETransferType type);
    List<TransferEntity> findBySenderIdAndTypeOrType(Long senderId, ETransferType type, ETransferType type2);

    List<TransferEntity> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

}
