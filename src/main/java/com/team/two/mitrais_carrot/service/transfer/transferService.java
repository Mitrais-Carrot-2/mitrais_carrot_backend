package com.team.two.mitrais_carrot.service.transfer;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.repository.TransferRepository;

public class transferService {
    private final TransferRepository transferHistoryRepository;

    public transferService(TransferRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }

    // TODO : Transfer Rewards -> Admin to user
    public TransferEntity TransferBarnReward(UserEntity user, int carrotAmount){
        int currentAmount = 0; //Tungg basket
        Long userId = user.getId();
        int updatedAmount = currentAmount + carrotAmount;
        // TODO : Update user basket

        Long adminId = 0l; //Kesepakatan admin id = 0

        TransferEntity transfer = new TransferEntity();
        transfer.setReceiverId(userId);
        transfer.setSenderId(adminId);
        transfer.setCarrotAmount(carrotAmount);

        return transferHistoryRepository.save(transfer);
    }

    //TODO : Transfer shared -> User to user

    //TODO : Transfer Bazaar -> What is this??

    // TODO : List all transfer

    
}
