package com.team.two.mitrais_carrot.repository.user;


import com.team.two.mitrais_carrot.entity.group.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {

    List<GroupEntity> findByManagerId(Long managerId);

    List<GroupEntity> findByManagerId_Id(Long id);

    List<GroupEntity> findByManagerIdIsGreaterThan(int managerId);

}
