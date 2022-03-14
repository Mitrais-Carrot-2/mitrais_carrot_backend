package com.team.two.mitrais_carrot.service.transferHistory;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.transferHistory.TransferHistoryEntity;
import com.team.two.mitrais_carrot.repository.TransferHistoryRepository;

public class transferHistoryService {
    private final TransferHistoryRepository transferHistoryRepository;

    public transferHistoryService(TransferHistoryRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }

    // TODO : Transfer Rewards -> Admin to user
    public TransferHistoryEntity TransferBarnReward(UserEntity user, int carrotAmount){
        int currentAmount = 0; //Tungg basket
        Long userId = user.getId();
        int updatedAmount = currentAmount + carrotAmount;
        // TODO : Update user basket

        Long adminId = 0l; //Kesepakatan admin id = 0

        TransferHistoryEntity transfer = new TransferHistoryEntity();
        transfer.setReceiverId(userId);
        transfer.setSenderId(adminId);
        transfer.setCarrotAmount(carrotAmount);

        return transferHistoryRepository.save(transfer);
    }

    //TODO : Transfer shared -> User to user

    //TODO : Transfer Bazaar -> What is this??

    
}
