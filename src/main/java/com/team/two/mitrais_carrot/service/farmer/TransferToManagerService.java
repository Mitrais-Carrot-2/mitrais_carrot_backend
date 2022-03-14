package com.team.two.mitrais_carrot.service.farmer;

import com.team.two.mitrais_carrot.dto.farmer.TransferToManagerDto;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.repository.farmer.TransferToManagerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class TransferToManagerService {
    TransferToManagerRepository transferToManagerRepository;
    BarnRepository barnRepository;

    public FreezerEntity sendToManager(TransferToManagerDto req){
        Integer barnId = barnRepository.findByIsActive(true).getId();

        FreezerEntity freezerEntity = new FreezerEntity();
        freezerEntity.setManagerId(req.getManagerId());
        freezerEntity.setCarrotAmount(req.getCarrotAmount());
        freezerEntity.setDistributedCarrot(0);
        freezerEntity.setShareAt(LocalDateTime.now());
        freezerEntity.setBarnId(barnId);

        return transferToManagerRepository.save(freezerEntity);
    }
}
