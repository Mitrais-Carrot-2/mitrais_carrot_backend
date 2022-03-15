package com.team.two.mitrais_carrot.service.farmer;

import com.team.two.mitrais_carrot.dto.farmer.BarnToFreezerDto;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.repository.farmer.TransferToManagerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class BarnToFreezerService {
    private final TransferToManagerRepository transferToManagerRepository;

    private final BarnRepository barnRepository;

    public BarnToFreezerService(TransferToManagerRepository transferToManagerRepository, BarnRepository barnRepository) {
        this.transferToManagerRepository = transferToManagerRepository;
        this.barnRepository = barnRepository;
    }

    public FreezerEntity sendToManager(BarnToFreezerDto req){
        Integer barnId = barnRepository.findByIsActive(true).getId();
        final BarnEntity barn = barnRepository.findById(barnId).get();
        barn.setCarrotAmount(barn.getCarrotAmount()-req.getCarrotAmount());

        FreezerEntity freezerEntity = new FreezerEntity();
        freezerEntity.setManagerId(req.getManagerId());
        freezerEntity.setCarrotAmount(req.getCarrotAmount());
        freezerEntity.setDistributedCarrot(0);
        freezerEntity.setShareAt(LocalDateTime.now());
        freezerEntity.setBarnId(barnId);

        FreezerEntity result = transferToManagerRepository.save(freezerEntity);
        barnRepository.save(barn);

        return result;
    }
}
