package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.dto.manager.FreezerDto;
import com.team.two.mitrais_carrot.dto.manager.FreezerHistoryDto;
import com.team.two.mitrais_carrot.dto.manager.TransferToGroupDto;
import com.team.two.mitrais_carrot.dto.manager.TransferToStaffDto;
import com.team.two.mitrais_carrot.dto.notification.NotificationDto;
import com.team.two.mitrais_carrot.dto.user.GroupDto;
import com.team.two.mitrais_carrot.dto.user.StaffDto;
import com.team.two.mitrais_carrot.dto.user.UserGroupDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.entity.transfer.ETransferType;
import com.team.two.mitrais_carrot.entity.transfer.FreezerTransferHistoryEntity;
import com.team.two.mitrais_carrot.entity.transfer.TransferEntity;
import com.team.two.mitrais_carrot.repository.*;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.repository.user.GroupRepository;
import com.team.two.mitrais_carrot.repository.user.ManagerRepository;
import com.team.two.mitrais_carrot.repository.user.UserGroupRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.security.services.UserDetailsImpl;

import com.team.two.mitrais_carrot.entity.basket.EBasket;
import com.team.two.mitrais_carrot.service.notification.NotificationService;
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
    BasketService basketService;

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    FreezerTransferHistoryRepository freezerTransferHistoryRepository;

    @Autowired
    NotificationService notificationService;

    Logger logger = LoggerFactory.getLogger(ManagerService.class);

    public Long getManagerId() {
        Long supervisorId = 1L;
        try {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            supervisorId = user.getId();
            logger.info("MANAGER ID: "+supervisorId);
        } catch (ClassCastException err) {
            logger.error("No Authorization / Supervisor not exist!");
        }

        return supervisorId;
    }

//    public List<StaffDto> fetchMyStaff(Long managerId){
    public List<StaffDto> fetchMyStaff(){
        List<UserEntity> staff = managerRepository.findBySupervisorId(getManagerId());
//        List<UserEntity> staff = managerRepository.findBySupervisorId(getManagerId());
        List<StaffDto> result = new ArrayList<>();
        staff.forEach(s ->
            result.add(new StaffDto(
                s.getId(),
                s.getUsername(),
                s.getFirstName(),
                s.getLastName(),
                s.getJobFamily(),
                s.getJobGrade(),
                s.getOffice())
            )
        );
        return result;
    }

    public Boolean transferToStaff(TransferToStaffDto req){
        if(req.getCarrotAmount() > 0){
            BarnEntity barn = barnRepository.findByIsActive(true);
            FreezerEntity freezer = freezerRepository.findByManagerIdAndBarn_Id(1L, barn.getId());

            BasketEntity oldBasket = basketService.getActiveBasket(req.getStaffId(), true);
            if(freezer.getCarrotAmount() - req.getCarrotAmount()>=0) {
                basketService.updateCarrot(oldBasket.getUser(), req.getCarrotAmount(), EBasket.SHARE);

                freezer.setCarrotAmount(freezer.getCarrotAmount() - req.getCarrotAmount());
                freezer.setDistributedCarrot(freezer.getDistributedCarrot() + req.getCarrotAmount());
                freezerRepository.save(freezer);

                TransferToStaffDto result = new TransferToStaffDto();

                result.setStaffId(oldBasket.getUser().getId());
                result.setCarrotAmount(oldBasket.getCarrotAmount());
                result.setNote(req.getNote());

                TransferEntity history = new TransferEntity();
                history.setSenderId(getManagerId());
                history.setReceiverId(req.getStaffId());
                history.setCarrotAmount(req.getCarrotAmount());
                history.setNote(req.getNote());
                history.setShareAt(LocalDateTime.now());
                history.setType(ETransferType.TYPE_SHARED);

                UserEntity user = userRepository.findById(req.getStaffId()).get();
                UserEntity manager = userRepository.findById(getManagerId()).get();

                FreezerTransferHistoryEntity freezerHistory = new FreezerTransferHistoryEntity();
                freezerHistory.setFreezer(freezer);
                freezerHistory.setReceiverId(user);
                freezerHistory.setManagerId(manager);
                freezerHistory.setShareAt(LocalDateTime.now());
                freezerHistory.setType(ETransferType.TYPE_SHARED_GROUP);
                freezerHistory.setNote(req.getNote());
                freezerHistory.setCarrotAmount(req.getCarrotAmount());
                freezerTransferHistoryRepository.save(freezerHistory);

                transferRepository.save(history);

                notificationService.createNotification(
                        new NotificationDto(
                                user.getId(),
                                "You just received "+req.getCarrotAmount()+" carrots from "+manager.getFirstName()+" "+manager.getLastName())
                );
                return true;
            }
        }

        return false;
    }

    public void transferToStaff(Long userId, Long carrot, String note, String managerName){
        BarnEntity barn = barnRepository.findByIsActive(true);
        FreezerEntity freezer = freezerRepository.findByManagerIdAndBarn_Id(getManagerId(), barn.getId());

        BasketEntity oldBasket = basketRepository.findByUser_IdAndBarn_Id(userId, barn.getId());

            basketService.updateCarrot(oldBasket.getUser(), carrot, EBasket.SHARE);

            freezer.setCarrotAmount(freezer.getCarrotAmount() - carrot);
            freezer.setDistributedCarrot(freezer.getDistributedCarrot() + carrot);
            freezerRepository.save(freezer);

            TransferToStaffDto result = new TransferToStaffDto();

            result.setStaffId(oldBasket.getUser().getId());
            result.setCarrotAmount(oldBasket.getCarrotAmount());
            result.setNote(note);

            TransferEntity history = new TransferEntity();
            history.setSenderId(Long.valueOf(freezer.getId()));
//            history.setSenderId(getManagerId());
            history.setReceiverId(userId);
            history.setCarrotAmount(carrot);
            history.setNote(note);
            history.setShareAt(LocalDateTime.now());
            history.setType(ETransferType.TYPE_SHARED);
            transferRepository.save(history);

            notificationService.createNotification(
                    new NotificationDto(userId, "You just received "+carrot+" carrots from "+managerName)
            );
//            return true;
//        }
//
//        return false;
    }

    public List<GroupDto> fetchMyGroup(){
        // logger.info("Fetch My Group "+getManagerId());
        List<GroupEntity> groups = groupRepository.findByManagerId_Id(getManagerId());

        // logger.error("Fetch My Group "+groups.size());
        List<GroupDto> groupDto = new ArrayList<>();
        groups.forEach(g -> {
            List<UserGroupEntity> staff = userGroupRepository.findByGroup_Id(g.getId());
            groupDto.add(new GroupDto(g.getId(), g.getName(), g.getAllocation(), staff.size(), g.getAllocation()*staff.size(), g.getNote()));
        });
        return groupDto;
    }

    public Boolean transferToGroup(TransferToGroupDto req){
        if(req.getCarrotAmount() > 0) {
            UserEntity manager = userRepository.findById(getManagerId()).get();
            String managerName = manager.getFirstName() + " " + manager.getLastName();
            Integer groupId = req.getGroupId();

            List<UserGroupEntity> members = userGroupRepository.findByGroup_Id(groupId);

            Long totalCarrot = members.size() * req.getCarrotAmount();
            if (totalCarrot - (req.getCarrotAmount() * members.size()) >= 0) {
                members.forEach(m -> {
                    transferToStaff(m.getUser().getId(), req.getCarrotAmount(), req.getNote(), managerName);
                });
                return true;
            }
        }
        return false;
    }

    public List<UserGroupDto> fetchMyStaffGroup(Integer groupId) {
        List<UserGroupEntity> members = userGroupRepository.findByGroup_Id(groupId);
        List<UserGroupDto> users = new ArrayList<>();
        members.forEach(m -> {
            UserEntity user = userRepository.findByUsername(m.getUser().getUsername());
            users.add(new UserGroupDto(user.getUsername(), user.getFirstName()+" "+user.getLastName(), user.getJobFamily(), user.getJobGrade(), user.getOffice()));
        });
        return users;
    }

    public FreezerDto getActiveFreezer(){
        BarnEntity barn = barnRepository.findByIsActive(true);
        FreezerEntity activeFreezer = freezerRepository.findByManagerIdAndBarn_Id(getManagerId(), barn.getId());
        FreezerDto freezer = new FreezerDto();
        freezer.setFreezerId(activeFreezer.getId());
        freezer.setBarnName(barn.getBarnName());
        freezer.setBarnOwner(barn.getUserId().getFirstName()+" "+barn.getUserId().getLastName());
        freezer.setStartDate(barn.getStartDate());
        freezer. setEndDate(barn.getEndDate());
        freezer.setCarrotAmount(activeFreezer.getCarrotAmount());
        freezer.setDistributedCarrot(activeFreezer.getDistributedCarrot());
        return freezer;
    }

    public List<FreezerHistoryDto> getFreezerHistory(){
        BarnEntity barn = barnRepository.findByIsActive(true);
        FreezerEntity activeFreezer = freezerRepository.findByManagerIdAndBarn_Id(getManagerId(), barn.getId());

        List<FreezerTransferHistoryEntity> transferHistory = freezerTransferHistoryRepository.findByFreezer_Id(activeFreezer.getId());
        List<FreezerHistoryDto> freezerHistory = new ArrayList<>();

        transferHistory.forEach(t -> {
            freezerHistory.add(
                new FreezerHistoryDto(
                        t.getReceiverId().getFirstName()+" "+t.getReceiverId().getLastName(),
                        t.getReceiverId().getJobFamily(),
                        t.getReceiverId().getJobGrade(),
                        t.getCarrotAmount(),
                        t.getNote(),
                        t.getShareAt()
                )
            );
        });
        return freezerHistory;
    }
}
