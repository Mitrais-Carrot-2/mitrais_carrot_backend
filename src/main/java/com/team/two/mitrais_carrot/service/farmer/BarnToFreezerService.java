package com.team.two.mitrais_carrot.service.farmer;

import com.team.two.mitrais_carrot.dto.farmer.BarnToFreezerDto;
import com.team.two.mitrais_carrot.dto.user.StaffDto;
import com.team.two.mitrais_carrot.entity.auth.ERole;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerHistoryEntity;
import com.team.two.mitrais_carrot.repository.FreezerHistoryRepository;
import com.team.two.mitrais_carrot.repository.FreezerRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.repository.farmer.TransferToManagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BarnToFreezerService {
    private final TransferToManagerRepository transferToManagerRepository;

    private final BarnRepository barnRepository;

    public BarnToFreezerService(TransferToManagerRepository transferToManagerRepository, BarnRepository barnRepository) {
        this.transferToManagerRepository = transferToManagerRepository;
        this.barnRepository = barnRepository;
    }

    @Autowired
    FreezerHistoryRepository freezerHistoryRepository;

    @Autowired
    FreezerRepository freezerRepository;

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(BarnToFreezerService.class);

    public Boolean sendToManager(BarnToFreezerDto req){
        BarnEntity barn = barnRepository.findByIsActive(true);
        Long barnBalance = barn.getCarrotAmount();
        Long freezerBalance = 0L;
        Long transferedCarrot = Math.abs(req.getCarrotAmount());
        FreezerHistoryEntity freezerHistory = new FreezerHistoryEntity();
        logger.info("Barn ID: "+barn.getId());
        logger.info("Manager ID: "+req.getManagerId());
        FreezerEntity freezerEntity = freezerRepository.findByManagerIdAndBarn_Id(req.getManagerId(), barn.getId());

        // UserEntity manager = userRepository.findById(req.getManagerId()).get();
        logger.error("Freezer: "+freezerEntity);
        if (freezerEntity==null){
            logger.info("----------------- Create New Freezer -----------------");
            freezerEntity = new FreezerEntity();
            freezerEntity.setDistributedCarrot(0L);
            // freezerEntity.setManagerId(manager);
            freezerEntity.setManagerId(req.getManagerId());
            freezerEntity.setBarn(barn);
        } else {
            logger.info("----------------- Update Freezer -----------------");
            freezerBalance = freezerEntity.getCarrotAmount();
        }
        if (barnBalance - transferedCarrot >= 0L) {
            freezerEntity.setCarrotAmount(freezerBalance+transferedCarrot);
            transferToManagerRepository.save(freezerEntity);

            freezerHistory.setFreezerId(freezerEntity);
            freezerHistory.setCarrotAmount(transferedCarrot);
            freezerHistory.setShareAt(LocalDateTime.now());
            freezerHistoryRepository.save(freezerHistory);

            barn.setCarrotAmount(barnBalance - transferedCarrot);
            barn.setDistributedCarrot(barn.getDistributedCarrot()+transferedCarrot);
            barnRepository.save(barn);

            return true;
        }
        return false;
    }

    public List<StaffDto> fetchAllManager() {
        List<StaffDto> staffDto = new ArrayList<>();
        List<UserEntity> staff = userRepository.findByRoles_Name(ERole.ROLE_MANAGER);
        for (UserEntity user : staff) {
            staffDto.add(
                    new StaffDto(
                            user.getId(),
                            user.getUsername(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getJobFamily(),
                            user.getJobGrade(),
                            user.getOffice()
                    ));
        }
        return staffDto;
    }
}
