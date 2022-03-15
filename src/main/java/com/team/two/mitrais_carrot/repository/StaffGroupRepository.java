package com.team.two.mitrais_carrot.repository;


import com.team.two.mitrais_carrot.entity.group.GroupEntity;
//import com.team.two.mitrais_carrot.entity.merchant.StaffGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffGroupRepository extends JpaRepository<GroupEntity, Integer> {

}
