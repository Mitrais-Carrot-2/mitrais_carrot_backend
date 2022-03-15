package com.team.two.mitrais_carrot.service.transfer;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.repository.TransferRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.basket.EBasket;
import com.team.two.mitrais_carrot.service.farmer.BarnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final TransferRepository transferHistoryRepository;
    @Autowired
    private BasketService basketService;
    @Autowired
    private BarnService barnService;

    public TransferService(TransferRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }

    // TODO : Transfer Rewards -> Admin to user
    public TransferEntity transferBarnReward(UserEntity user, Long carrotAmount, ETransferType type) {
        BarnEntity activeBarn = barnService.isActiveBarn(true);
        // Integer activeBarn = 1;
//        BarnEntity barn = barnService.getBarnById(activeBarn);
        if (activeBarn.getCarrotAmount() >= carrotAmount) {
            Long currentAmount = basketService.getRewardCarrot(user);
            Long userId = user.getId();
            Long updatedAmount = currentAmount + carrotAmount;
            basketService.updateCarrot(user, updatedAmount, EBasket.REWARD);

            barnService.shareCarrot(carrotAmount, activeBarn.getBarnId());

            Long adminId = 0l; // Kesepakatan admin id = 0

            TransferEntity transfer = new TransferEntity();
            transfer.setReceiverId(userId);
            transfer.setSenderId(adminId);
            transfer.setCarrotAmount(carrotAmount);
            transfer.setType(type);

            return transferHistoryRepository.save(transfer);
        }
        return null;

    }

    // TODO : Transfer shared -> User to user or User to Group

    // TODO : Transfer Bazaar -> User to Bazaar

    // TODO : List all transfer

}
