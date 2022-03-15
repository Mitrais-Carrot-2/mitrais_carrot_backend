package com.team.two.mitrais_carrot.controller.user;

import com.team.two.mitrais_carrot.dto.user.GroupDto;
import com.team.two.mitrais_carrot.dto.user.StaffDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.service.user.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    // TODO List of Staff
    @GetMapping("/staff")
    public List<StaffDto> fetchMyStaff(){
        return managerService.fetchMyStaff();
    }

    // TODO List of Group
    @GetMapping("/group")
    public List<GroupDto> fetchMyGroup(){
        return managerService.fetchMyGroup();
    }

    // TODO List of Group Staff
}
