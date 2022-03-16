package com.team.two.mitrais_carrot.service.transfer;

import java.time.LocalDate;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.repository.TransferRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.basket.EBasket;
import com.team.two.mitrais_carrot.service.farmer.BarnService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    @Autowired
    private BasketService basketService;
    @Autowired
    private BarnService barnService;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    Logger logger = org.slf4j.LoggerFactory.getLogger(TransferService.class);


    // Transfer Rewards -> Admin to user
    public TransferEntity transferBarnReward(UserEntity user, Long carrotAmount, ETransferType type) {
        
        BarnEntity activeBarn = barnService.isActiveBarn(true);
        BarnEntity barn = barnService.getBarnById(activeBarn.getId());
        
        if (barn.getCarrotAmount() >= carrotAmount) {
            // BasketEntity activeUserBasket = basketService.getActiveBasket(user, true);//ada error tdk bisa menemukan basket
            Long userId = user.getId();
            basketService.updateCarrot(user, carrotAmount, EBasket.REWARD);
            
            barnService.shareCarrot(carrotAmount, activeBarn.getId());
            
            Long adminId = 0l; // Kesepakatan admin id = 0
            
            TransferEntity transfer = new TransferEntity();
            transfer.setReceiverId(userId);
            transfer.setSenderId(adminId);
            transfer.setCarrotAmount(carrotAmount);
            transfer.setType(type);
            transfer.setTime(LocalDate.now());
            transfer.setDescription("Happy Birthday!!");

            return transferRepository.save(transfer);
        }
        return null;

    }

    // TODO : Transfer shared -> User to user or User to Group

    // TODO : Transfer Bazaar -> User to Bazaar

    // TODO : List all transfer

}
