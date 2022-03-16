package com.team.two.mitrais_carrot.controller.user;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.manager.TransferToGroupDto;
import com.team.two.mitrais_carrot.dto.manager.TransferToStaffDto;
import com.team.two.mitrais_carrot.dto.user.GroupDto;
import com.team.two.mitrais_carrot.dto.user.StaffDto;
import com.team.two.mitrais_carrot.dto.user.UserGroupDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.basket.BasketEntity;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.service.user.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/staff")
    public List<StaffDto> fetchMyStaff(){
        return managerService.fetchMyStaff();
    }

    @PostMapping("/transfer/staff")
    public ResponseEntity<MessageDto> transferToStaff(TransferToStaffDto req){
        Boolean status = managerService.transferToStaff(req);
        if (status){
            return ResponseEntity.ok(new MessageDto("Transfer from Manager to Staff success!", true));
        } else {
            return ResponseEntity.badRequest().body(new MessageDto("Not enough carrot in Freezer!", false));
        }
    }

    @GetMapping("/group")
    public List<GroupDto> fetchMyGroup(){
        return managerService.fetchMyGroup();
    }

    @GetMapping("/group/staff/{groupId}")
    public List<UserGroupDto> fetchMyStaffGroup(@PathVariable("groupId") Integer groupId){
        return managerService.fetchMyStaffGroup(groupId);
    }

    @PostMapping("/transfer/group")
    public ResponseEntity<MessageDto> transferToGroup(TransferToGroupDto req){
        Boolean status = managerService.transferToGroup(req);
        if (status){
            return ResponseEntity.ok(new MessageDto("Transfer from Manager to Group success!", true));
        } else {
            return ResponseEntity.badRequest().body(new MessageDto("Not enough carrot in Freezer!", false));
        }
    }
}
