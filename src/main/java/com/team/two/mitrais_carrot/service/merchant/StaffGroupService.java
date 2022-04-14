package com.team.two.mitrais_carrot.service.merchant;

import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.merchant.*;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import com.team.two.mitrais_carrot.repository.UserRepository;
import com.team.two.mitrais_carrot.repository.user.GroupRepository;
import com.team.two.mitrais_carrot.repository.user.UserGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public List<GroupDto> getAllGroups(){
        List<GroupEntity> groups = groupRepository.findAll();
        List<GroupDto> result = new ArrayList<>();
        groups.forEach(g -> {
            Integer member = userGroupRepository.findByGroup_Id(g.getId()).size();
            result.add(
                new GroupDto(
                    g.getId(),
                    g.getName(),
                    g.getAllocation(),
                    member,
                    member*g.getAllocation(),
                    g.getNote()
                )
            );
        });

        return result;
    }

    public GroupEntity getGroupById(Integer id){
        return groupRepository.getById(id);
    }

    Logger logger = LoggerFactory.getLogger(StaffGroupService.class);
    public ResponseEntity<?> createStaffGroup(StaffGroupDto request){
        GroupEntity group = new GroupEntity();
        UserEntity manager = userRepository.getById(Long.valueOf(request.getManagerId()));
        if (request.getName()=="" || request.getAllocation() < 1){
            return ResponseEntity.badRequest().body(new MessageDto("Error: Missing Data!", false));
        }
        group.setName(request.getName());
        group.setAllocation(request.getAllocation());
        group.setNote(request.getNote());
        group.setManagerId(manager);
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

    public StaffGroupDetailDto getGroupDetail(Integer id){
        GroupEntity rawGroup = groupRepository.getById(id);
        StaffGroupDetailDto result = new StaffGroupDetailDto();
        result.setId(rawGroup.getId());
        result.setName(rawGroup.getName());
//        result.setManagerId(Math.toIntExact(rawGroup.getManagerId()));
        result.setManagerName(rawGroup.getManagerId().getFirstName()+" "+rawGroup.getManagerId().getLastName());
        result.setAllocation(rawGroup.getAllocation());
        result.setNote(rawGroup.getNote());
        return result;
    }

    public ResponseEntity<?> updateStaffGroup(StaffGroupDto request, Integer id){
        GroupEntity group = groupRepository.getById(id);
        UserEntity manager = userRepository.getById(Long.valueOf(request.getManagerId()));
        if (request.getName()=="" || request.getAllocation() < 1){
            return ResponseEntity.badRequest().body(new MessageDto("Failed: Missing Data!", false));
        }
        group.setName(request.getName());
        group.setNote(request.getNote());
        group.setAllocation(request.getAllocation());
        group.setManagerId(manager);
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
