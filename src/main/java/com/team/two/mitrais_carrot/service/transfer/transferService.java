package com.team.two.mitrais_carrot.service.transfer;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.repository.TransferRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.basket.EBasket;

public class transferService {
    private final TransferRepository transferHistoryRepository;
    private BasketService basketService;

    public transferService(TransferRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }

    // TODO : Transfer Rewards -> Admin to user
    public TransferEntity TransferBarnReward(UserEntity user, Long carrotAmount, ETransferType type){
        Long currentAmount = basketService.getRewardCarrot(user);
        Long userId = user.getId();
        Long updatedAmount = currentAmount + carrotAmount;
        basketService.updateCarrot(user, updatedAmount, EBasket.REWARD);

        Long adminId = 0l; //Kesepakatan admin id = 0

        TransferEntity transfer = new TransferEntity();
        transfer.setReceiverId(userId);
        transfer.setSenderId(adminId);
        transfer.setCarrotAmount(carrotAmount);
        transfer.setType(type);

        return transferHistoryRepository.save(transfer);
    }

    //TODO : Transfer shared -> User to user or User to Group

    //TODO : Transfer Bazaar -> User to Bazaar

    // TODO : List all transfer

    
}
