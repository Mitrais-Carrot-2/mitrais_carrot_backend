package com.team.two.mitrais_carrot.service.transfer;

import java.time.LocalDateTime;
import java.util.List;

import com.team.two.mitrais_carrot.dto.farmer.BarnToFreezerDto;
import com.team.two.mitrais_carrot.dto.farmer.DistributeDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.repository.TransferRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.entity.basket.EBasket;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import com.team.two.mitrais_carrot.service.farmer.BarnToFreezerService;

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
    @Autowired
    private BarnToFreezerService barnToFreezerService;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    Logger logger = org.slf4j.LoggerFactory.getLogger(TransferService.class);

    public TransferEntity add(TransferEntity transfer){
        return transferRepository.save(transfer);
    }

    public List<TransferEntity> getAll(){
        return (List<TransferEntity>) transferRepository.findAll();
    }

    // Transfer Rewards -> Admin to user
    public TransferEntity transferBarnReward(UserEntity user, Long carrotAmount, ETransferType type) {
        
        BarnEntity activeBarn = barnService.isActiveBarn(true);
        
        if (activeBarn.getCarrotAmount() >= carrotAmount) {
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
            transfer.setShareAt(LocalDateTime.now());
            transfer.setNote("Happy Birthday!!");

            return transferRepository.save(transfer);
        }
        return null;

    }

    // Transfer Barn -> Freezer
    public TransferEntity transferBarnToFreezer(DistributeDto req) {

        BarnToFreezerDto transferDto = new BarnToFreezerDto();
        transferDto.setManagerId(req.getManagerId());
        transferDto.setCarrotAmount(req.getCarrotAmount());
        
        barnToFreezerService.sendToManager(transferDto);
        
        Long barnIdLong = Long.valueOf(req.getBarnId());
        
        TransferEntity transfer = new TransferEntity();
        transfer.setReceiverId(req.getManagerId());
        transfer.setSenderId(barnIdLong);
        transfer.setCarrotAmount(req.getCarrotAmount());
        transfer.setType(ETransferType.TYPE_BARN_TO_FREEZER);
        transfer.setShareAt(LocalDateTime.now());
        transfer.setNote(req.getNote());

        return transferRepository.save(transfer);
        


    }

    public List<TransferEntity> getBarnToFreezerTransfer(int id) {
        Long barnId = Long.valueOf(id);
        return transferRepository.findBySenderIdAndType(barnId, ETransferType.TYPE_BARN_TO_FREEZER);
    }

    // TODO : Transfer shared -> User to user or User to Group

    // TODO : Transfer Bazaar -> User to Bazaar

    // TODO : List all transfer

}
