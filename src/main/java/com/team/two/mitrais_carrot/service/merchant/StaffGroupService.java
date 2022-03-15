package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.dto.merchant.NewGroupMemberDto;
import com.team.two.mitrais_carrot.dto.merchant.StaffGroupDto;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.repository.user.GroupRepository;
import com.team.two.mitrais_carrot.repository.user.UserGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffGroupService {
    GroupRepository groupRepository;
    UserGroupRepository userGroupRepository;

    public StaffGroupService(GroupRepository groupRepository, UserGroupRepository userGroupRepository){
        this.groupRepository = groupRepository;
        this.userGroupRepository = userGroupRepository;
    }

    public List<GroupEntity> getAllGroups(){
        return (List<GroupEntity>) groupRepository.findAll();
    }

    public GroupEntity getGroupById(Integer id){
        return groupRepository.getById(id);
    }

    public GroupEntity createStaffGroup(StaffGroupDto request){
        GroupEntity group = new GroupEntity();
        group.setName(request.getName());
        group.setManagerId(request.getManagerId());
        return groupRepository.save(group);
    }

    public GroupEntity updateStaffGroup(StaffGroupDto request, Integer id){
        GroupEntity group = groupRepository.getById(id);
        group.setName(request.getName());
        group.setManagerId(request.getManagerId());
        return groupRepository.save(group);
    }

    public UserGroupEntity addNewMember(Integer id, NewGroupMemberDto request){
        List<UserGroupEntity> compare = userGroupRepository.findAll();
        compare = compare.stream().filter(s -> s.getGroupId() == id && s.getUserId() == request.getUserId()).collect(Collectors.toList());
        if (compare.isEmpty()){
            UserGroupEntity member = new UserGroupEntity();
            member.setGroupId(id);
            member.setUserId(request.getUserId());
            return userGroupRepository.save(member);
        }
        return null;
    }

    public List<UserGroupEntity> getInGroupStaff(){
        return (List<UserGroupEntity>) userGroupRepository.findAll();
    }
}
