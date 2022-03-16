package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.merchant.NewGroupMemberDto;
import com.team.two.mitrais_carrot.dto.merchant.StaffGroupDto;
import com.team.two.mitrais_carrot.dto.merchant.StaffListInGroupDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.repository.user.GroupRepository;
import com.team.two.mitrais_carrot.repository.user.UserGroupRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffGroupService {
    GroupRepository groupRepository;
    UserGroupRepository userGroupRepository;
    UserRepository userRepository;

    public StaffGroupService(GroupRepository groupRepository, UserGroupRepository userGroupRepository, UserRepository userRepository){
        this.groupRepository = groupRepository;
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
    }

    public List<GroupEntity> getAllGroups(){
        return (List<GroupEntity>) groupRepository.findAll();
    }

    public GroupEntity getGroupById(Integer id){
        return groupRepository.getById(id);
    }

    public ResponseEntity<?> createStaffGroup(StaffGroupDto request){
        GroupEntity group = new GroupEntity();
        group.setName(request.getName());
        group.setAllocation(request.getAllocation());
        group.setNote(request.getNote());
        group.setManagerId((long) request.getManagerId());
        groupRepository.save(group);
        return ResponseEntity.ok(new MessageDto("Staff Group Created!", true));
    }

    public List<StaffListInGroupDto> getStaffListInGroup(Integer id){
        List<UserGroupEntity> userGroup = userGroupRepository.findByGroup_Id(id);
        List<StaffListInGroupDto> userDto = userGroup.stream()
                .map((UserGroupEntity user) -> new StaffListInGroupDto(user.getUser().getUsername(), user.getUser().getFirstName() + user.getUser().getLastName(), user.getUser().getJobFamily(), user.getUser().getJobGrade(), user.getUser().getOffice()))
                .collect(Collectors.toList());

        return userDto;
    }

    public ResponseEntity<?> updateStaffGroup(StaffGroupDto request, Integer id){
        GroupEntity group = groupRepository.getById(id);
        group.setName(request.getName());
        group.setNote(request.getNote());
        group.setAllocation(request.getAllocation());
        group.setManagerId((long) request.getManagerId());
        groupRepository.save(group);
        return ResponseEntity.ok(new MessageDto("Group Details Updated!", true));
    }

    public ResponseEntity<?> addNewMember(int id, NewGroupMemberDto request){
        List<UserGroupEntity> userGroupCheck = userGroupRepository.findByGroup_Id(id);
        userGroupCheck = userGroupCheck.stream()
                .filter((UserGroupEntity user) -> user.getUser().getId() == (long) request.getUserId())
                .collect(Collectors.toList());
        UserGroupEntity userGroup = new UserGroupEntity();
        GroupEntity checker = groupRepository.getById(id);
        UserEntity userChecker = userRepository.getById((long) request.getUserId());
        if(userGroupCheck.isEmpty()) {
            userGroup.setGroup(checker);
            userGroup.setUser(userChecker);
//            System.out.println(userGroup.getGroup().getId() + " " + userGroup.getUser());
            userGroupRepository.save(userGroup);
            return ResponseEntity.ok(new MessageDto("Success Add Staff to Group!", true));
        }else{
            return ResponseEntity.badRequest().body(new MessageDto("Error: Staff has already added!", false));
        }
    }

    public List<UserGroupEntity> getInGroupStaff(){
        return (List<UserGroupEntity>) userGroupRepository.findAll();
    }
}
