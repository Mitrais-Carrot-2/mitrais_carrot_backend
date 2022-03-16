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
import com.team.two.mitrais_carrot.repository.BasketRepository;
import com.team.two.mitrais_carrot.repository.FreezerHistoryRepository;
import com.team.two.mitrais_carrot.repository.FreezerRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.repository.user.GroupRepository;
import com.team.two.mitrais_carrot.repository.user.ManagerRepository;
import com.team.two.mitrais_carrot.repository.user.UserGroupRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    Logger logger = LoggerFactory.getLogger(ManagerService.class);

//        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long supervisorId = user.getId();
    Long supervisorId = 2L;

    public List<StaffDto> fetchMyStaff(){
        List<UserEntity> staff = managerRepository.findBySupervisorId(supervisorId);
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
        FreezerEntity freezer = freezerRepository.findByManagerIdAndBarn_Id(supervisorId, barn.getId());

        BasketEntity oldBasket = basketService.getActiveBasket(req.getStaffId(), true);

        if(freezer.getCarrotAmount() - req.getCarrotAmount()>=0) {
            oldBasket.setBarn(barn);
            oldBasket.setCarrotAmount(oldBasket.getShareCarrot() + req.getCarrotAmount());
            oldBasket.setShareCarrot(oldBasket.getShareCarrot() + req.getCarrotAmount());
            basketRepository.save(oldBasket);

            freezer.setCarrotAmount(freezer.getCarrotAmount() - req.getCarrotAmount());
            freezer.setDistributedCarrot(freezer.getDistributedCarrot() + req.getCarrotAmount());
            freezerRepository.save(freezer);

            TransferToStaffDto result = new TransferToStaffDto();

            result.setStaffId(oldBasket.getUser().getId());
            result.setCarrotAmount(oldBasket.getCarrotAmount());
            result.setNote(req.getNote());
            return true;
        }

        return false;
    }

    public List<GroupDto> fetchMyGroup(){
        List<GroupEntity> groups = groupRepository.findByManagerId(supervisorId);

        List<GroupDto> groupDto = new ArrayList<>();
        groups.forEach(g -> {
            List<UserGroupEntity> staff = userGroupRepository.findByGroup_Id(g.getId());
            groupDto.add(new GroupDto(g.getId(), g.getName(), g.getAllocation(), staff.size(), g.getAllocation()*staff.size(), g.getNote()));
        });
        return groupDto;
    }
}
