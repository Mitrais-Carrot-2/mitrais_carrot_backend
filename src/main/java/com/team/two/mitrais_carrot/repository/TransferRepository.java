package com.team.two.mitrais_carrot.repository;

import java.util.List;

import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferEntity, Integer> {

    List<TransferEntity> findBySenderIdAndType(Long senderId, ETransferType type);

    List<TransferEntity> findBySenderIdAndTypeOrSenderIdAndType(Long senderId, ETransferType type, Long senderId2, ETransferType type2);

    List<TransferEntity> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

}
