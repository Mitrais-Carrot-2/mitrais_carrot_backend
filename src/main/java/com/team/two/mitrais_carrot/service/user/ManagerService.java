package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.dto.manager.TransferToStaffDto;
import com.team.two.mitrais_carrot.dto.user.GroupDto;
import com.team.two.mitrais_carrot.dto.user.StaffDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerHistoryEntity;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.repository.*;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.repository.user.GroupRepository;
import com.team.two.mitrais_carrot.repository.user.ManagerRepository;
import com.team.two.mitrais_carrot.repository.user.UserGroupRepository;
import com.team.two.mitrais_carrot.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    BarnRepository barnRepository;

    @Autowired
    FreezerRepository freezerRepository;

    @Autowired
    TransferRepository transferRepository;

    Logger logger = LoggerFactory.getLogger(ManagerService.class);

    public Long getManagerId() {
        Long supervisorId = 2L;
        try {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            supervisorId = user.getId();
        } catch (ClassCastException err) {
            logger.error("No Authorization / Supervisor not exist!");
            supervisorId = 2L;
        }

        return supervisorId;
    }

    public List<StaffDto> fetchMyStaff(){
        List<UserEntity> staff = managerRepository.findBySupervisorId(getManagerId());
        List<StaffDto> result = new ArrayList<>();
        staff.forEach(s ->
            result.add(new StaffDto(
                s.getId(),
                s.getUsername(),
                s.getFirstName(),
                s.getLastName(),
                s.getJobFamily(),
                s.getJobGrade())
            )
        );
        return result;
    }

    public Boolean transferToStaff(TransferToStaffDto req){
        BarnEntity barn = barnRepository.findByIsActive(true);
        FreezerEntity freezer = freezerRepository.findByManagerIdEqualsAndBarnId_BarnIdEquals(getManagerId(), barn.getBarnId());

        BasketEntity oldBasket = basketRepository.findByUserId_IdAndBarnId_BarnId(req.getStaffId(), barn.getBarnId());

        if(freezer.getCarrotAmount() - req.getCarrotAmount()>=0) {
            oldBasket.setBarnId(barn);
            oldBasket.setCarrotAmount(oldBasket.getShareCarrot() + req.getCarrotAmount());
            oldBasket.setShareCarrot(oldBasket.getShareCarrot() + req.getCarrotAmount());
            basketRepository.save(oldBasket);

            freezer.setCarrotAmount(freezer.getCarrotAmount() - req.getCarrotAmount());
            freezer.setDistributedCarrot(freezer.getDistributedCarrot() + req.getCarrotAmount());
            freezerRepository.save(freezer);

            TransferToStaffDto result = new TransferToStaffDto();

            result.setStaffId(oldBasket.getUserId().getId());
            result.setCarrotAmount(oldBasket.getCarrotAmount());
            result.setNote(req.getNote());

            TransferEntity history = new TransferEntity();
            history.setSenderId(getManagerId());
            history.setReceiverId(req.getStaffId());
            history.setCarrotAmount(req.getCarrotAmount());
            history.setNote(req.getNote());
            history.setShareAt(LocalDateTime.now());
            history.setType(ETransferType.TYPE_SHARED);
            transferRepository.save(history);

            return true;
        }

        return false;
    }

    public List<GroupDto> fetchMyGroup(){
        List<GroupEntity> groups = groupRepository.findByManagerId(getManagerId());

        List<GroupDto> groupDto = new ArrayList<>();
        groups.forEach(g -> {
            List<UserGroupEntity> staff = userGroupRepository.findByGroup_Id(g.getId());
            groupDto.add(new GroupDto(g.getId(), g.getName(), g.getAllocation(), staff.size(), g.getAllocation()*staff.size(), g.getNote()));
        });
        return groupDto;
    }

//    public Boolean transferToGroup(TransferToGroupDto){
//
//    }
}
