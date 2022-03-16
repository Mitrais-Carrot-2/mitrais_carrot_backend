package com.team.two.mitrais_carrot.service.user;

import com.team.two.mitrais_carrot.dto.manager.TransferToStaffDto;
import com.team.two.mitrais_carrot.dto.user.GroupDto;
import com.team.two.mitrais_carrot.dto.user.StaffDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.farmer.BarnEntity;
import com.team.two.mitrais_carrot.entity.freezer.FreezerEntity;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.repository.BasketRepository;
import com.team.two.mitrais_carrot.repository.farmer.BarnRepository;
import com.team.two.mitrais_carrot.repository.user.GroupRepository;
import com.team.two.mitrais_carrot.repository.user.UserGroupRepository;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.repository.user.ManagerRepository;
import com.team.two.mitrais_carrot.service.basket.BasketService;
import com.team.two.mitrais_carrot.service.farmer.BarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    UserService userService;

    @Autowired
    BarnService barnService;

    @Autowired
    BasketService basketService;

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

    public TransferToStaffDto transferToStaff(TransferToStaffDto req){
        BarnEntity barn = barnService.isActiveBarn(true);
        BasketEntity oldBasket = basketService.getActiveBasket(req.getStaffId(), true);

        UserEntity user = userService.getById(req.getStaffId());

        BasketEntity newBasket = new BasketEntity(user, barn, oldBasket.getShareCarrot() + req.getCarrotAmount(), 0, oldBasket.getShareCarrot() + req.getCarrotAmount(), 0);
        basketRepository.save(newBasket);

        TransferToStaffDto result = new TransferToStaffDto();
        result.setStaffId(newBasket.getUser().getId());
        result.setCarrotAmount(newBasket.getCarrotAmount());
        result.setSharedAmount(newBasket.getShareCarrot());
        result.setNote(req.getNote());

        return result;
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
