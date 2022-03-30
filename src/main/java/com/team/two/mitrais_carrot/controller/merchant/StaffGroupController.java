package com.team.two.mitrais_carrot.controller.merchant;


import com.team.two.mitrais_carrot.dto.merchant.NewGroupMemberDto;
import com.team.two.mitrais_carrot.dto.merchant.StaffGroupDto;
import com.team.two.mitrais_carrot.dto.merchant.StaffListInGroupDto;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
//import com.team.two.mitrais_carrot.entity.merchant.StaffGroupEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.service.merchant.StaffGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bazaar/group")
public class StaffGroupController {
    @Autowired
    StaffGroupService staffGroupService;
    public StaffGroupController(StaffGroupService staffGroupService){
        this.staffGroupService = staffGroupService;
    }

    @GetMapping("")
    public List<GroupEntity> getAllStaffGroup(){
        return staffGroupService.getAllGroups();
    }

    @PostMapping("")
    public ResponseEntity<?> createStaffGroup(@RequestBody StaffGroupDto request){
        return staffGroupService.createStaffGroup(request);
    }

    @GetMapping("{id}")
    public List<StaffListInGroupDto> getStaffListInGroup(@PathVariable("id")int id){
        return staffGroupService.getStaffListInGroup(id);
    }

    @GetMapping("/details/{id}")
    public StaffGroupDto getGroupDetail(@PathVariable("id")int id){
        return staffGroupService.getGroupDetail(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStaffGroup(@PathVariable("id") int id, @RequestBody StaffGroupDto request){
        return staffGroupService.updateStaffGroup(request,id);
    }

    //add employee/staff/user into groups
    @PostMapping("{id}")
    public ResponseEntity<?> addStaffToGroup(@PathVariable("id") int id, @RequestBody NewGroupMemberDto request){
        return staffGroupService.addNewMember(id, request);
    }

    //naming is hard
    @GetMapping("/stafflist")
    public List<UserGroupEntity> getInGroupStaff(){
        return staffGroupService.getInGroupStaff();
    }
}
