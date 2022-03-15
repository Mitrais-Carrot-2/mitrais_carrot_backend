package com.team.two.mitrais_carrot.service.transfer;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.repository.TransferRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.basket.EBasket;
import com.team.two.mitrais_carrot.service.farmer.BarnService;

public class TransferService {
    private final TransferRepository transferHistoryRepository;
    private BasketService basketService;
    private BarnService barnService;

    public TransferService(TransferRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }

    // TODO : Transfer Rewards -> Admin to user
    public TransferEntity transferBarnReward(UserEntity user, Long carrotAmount, ETransferType type) {
        Integer activeBarn = barnService.isActiveBarn(true);
        BarnEntity barn = barnService.getBarnById(activeBarn);
        if (barn.getCarrotAmount() >= carrotAmount) {
            Long currentAmount = basketService.getRewardCarrot(user);
            Long userId = user.getId();
            Long updatedAmount = currentAmount + carrotAmount;
            basketService.updateCarrot(user, updatedAmount, EBasket.REWARD);

            barnService.shareCarrot(carrotAmount, activeBarn);

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
