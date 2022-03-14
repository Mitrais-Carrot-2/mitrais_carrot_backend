package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.dto.merchant.NewGroupMemberDto;
import com.team.two.mitrais_carrot.dto.merchant.StaffGroupDto;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.entity.userGroup.UserGroupEntity;
import com.team.two.mitrais_carrot.repository.StaffGroupRepository;
import com.team.two.mitrais_carrot.repository.UserGroupRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StaffGroupService {
    StaffGroupRepository staffGroupRepository;
    UserGroupRepository userGroupRepository;

    public StaffGroupService(StaffGroupRepository staffGroupRepository, UserGroupRepository userGroupRepository){
        this.staffGroupRepository = staffGroupRepository;
        this.userGroupRepository = userGroupRepository;
    }





    public List<GroupEntity> getAllGroups(){
        return (List<GroupEntity>) staffGroupRepository.findAll();
    }

    public GroupEntity getGroupById(Integer id){
        return staffGroupRepository.getById(id);
    }

    public GroupEntity createStaffGroup(StaffGroupDto request){
        GroupEntity group = new GroupEntity();
        group.setName(request.getName());
        group.setUserId(request.getUserId());
        return staffGroupRepository.save(group);
    }

    public GroupEntity updateStaffGroup(StaffGroupDto request, Integer id){
        GroupEntity group = staffGroupRepository.getById(id);
        group.setName(request.getName());
        group.setUserId(request.getUserId());
        return staffGroupRepository.save(group);
    }

    public UserGroupEntity addNewMember(Integer id, NewGroupMemberDto request){
        UserGroupEntity member = new UserGroupEntity();
        member.setGroupId(id);
        member.setUserId(request.getUserId());
        return userGroupRepository.save(member);
    }

    public List<UserGroupEntity> getInGroupStaff(){
        return (List<UserGroupEntity>) userGroupRepository.findAll();
    }
}
