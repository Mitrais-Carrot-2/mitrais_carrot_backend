package com.team.two.mitrais_carrot.service.farmer;

import com.team.two.mitrais_carrot.dto.farmer.BarnToFreezerDto;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.repository.FreezerRepository;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.repository.farmer.TransferToManagerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class BarnToFreezerService {
    private final TransferToManagerRepository transferToManagerRepository;

    private final BarnRepository barnRepository;

    public BarnToFreezerService(TransferToManagerRepository transferToManagerRepository, BarnRepository barnRepository) {
        this.transferToManagerRepository = transferToManagerRepository;
        this.barnRepository = barnRepository;
    }

    @Autowired
    FreezerRepository freezerRepository;

    public Boolean sendToManager(BarnToFreezerDto req){
//        Integer barnId = barnRepository.findByIsActive(true).getId();
        BarnEntity barn = barnRepository.findByIsActive(true);
        Long barnBalance = barn.getCarrotAmount();
        Long transferedCarrot = Math.abs(req.getCarrotAmount());

        FreezerEntity freezer = freezerRepository.findByBarnId_FreezerId_BarnIdAndBarnId_FreezerId_ManagerId(barn, req.getManagerId());
        FreezerEntity freezerEntity;

        try {
            freezerEntity = freezer;
            freezerEntity.setCarrotAmount(freezerEntity.getCarrotAmount()+req.getCarrotAmount());
        } catch (NullPointerException err){
            freezerEntity = new FreezerEntity();
            freezerEntity.setDistributedCarrot(0L);
            freezerEntity.setManagerId(req.getManagerId());
            freezerEntity.setBarnId(barn);
            freezerEntity.setCarrotAmount(req.getCarrotAmount());
        }

        if (barnBalance - transferedCarrot >= 0) {
            barn.setCarrotAmount(barnBalance - transferedCarrot);
            barn.setDistributedCarrot(barn.getDistributedCarrot()+transferedCarrot);

            transferToManagerRepository.save(freezerEntity);
            barnRepository.save(barn);
            return true;
        }
        return false;
    }
}
