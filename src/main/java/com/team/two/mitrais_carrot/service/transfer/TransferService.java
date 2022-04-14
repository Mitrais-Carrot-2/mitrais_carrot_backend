package com.team.two.mitrais_carrot.service.transfer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.team.two.mitrais_carrot.dto.farmer.BarnToFreezerDto;
import com.team.two.mitrais_carrot.dto.farmer.DistributeDto;
import com.team.two.mitrais_carrot.dto.transfer.TransferHistoryDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.repository.TransferRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.entity.basket.EBasket;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import com.team.two.mitrais_carrot.service.farmer.BarnToFreezerService;

import com.team.two.mitrais_carrot.service.user.UserService;
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
    @Autowired
    private UserService userService;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }


    public TransferEntity add(TransferEntity transfer) {
        return transferRepository.save(transfer);
    }

    public List<TransferEntity> getAll() {
        return (List<TransferEntity>) transferRepository.findAll();
    }

    public List<TransferHistoryDto> getAllById(Long userId) {
        List<TransferHistoryDto> listTransferHistoryDto = new ArrayList<>();
        List<TransferEntity> transfers = (List<TransferEntity>) transferRepository.findBySenderIdOrReceiverId(userId,
                userId);

        transfers.stream()
                .forEach(t -> {
                    String receiverName = "";
                    String senderName = "";
                    String userName = "";
                    if (t.getReceiverId() == 999)
                        receiverName = "BAZAAR";
                    else
                        receiverName = userService.getById(t.getReceiverId()).getUsername();

                    if (t.getSenderId() == 999)
                        senderName = "BAZAAR";
                    else
                        senderName = userService.getById(t.getSenderId()).getUsername();

                    if (userService.getById(userId).getUsername() != receiverName)
                        userName = receiverName;
                    else if (userService.getById(userId).getUsername() != senderName)
                        userName = senderName;

                    TransferHistoryDto transferHistoryDto = new TransferHistoryDto(
                            t.getTransferId(),
                            t.getReceiverId(),
                            t.getSenderId(),
                            userName,
                            t.getShareAt(),
                            t.getType(),
                            t.getNote(),
                            t.getCarrotAmount());
                    listTransferHistoryDto.add(transferHistoryDto);
                });
        return listTransferHistoryDto;
    }

    // Transfer Rewards -> Admin to user
    public TransferEntity transferBarnReward(UserEntity user, Long carrotAmount, ETransferType type) {
        BarnEntity activeBarn = barnService.isActiveBarn(true);

        if (activeBarn.getCarrotAmount() >= carrotAmount) {
            Long userId = user.getId();
            basketService.updateCarrot(user, carrotAmount, EBasket.REWARD);

            barnService.shareCarrot(carrotAmount, activeBarn.getId());

            Long barnIdLong = Long.valueOf(activeBarn.getId());

            TransferEntity transfer = new TransferEntity();
            transfer.setReceiverId(userId);
            transfer.setSenderId(barnIdLong);
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
        return transferRepository.findBySenderIdAndTypeOrType(barnId, ETransferType.TYPE_BARN_TO_FREEZER,
                ETransferType.TYPE_REWARD);
    }

}
