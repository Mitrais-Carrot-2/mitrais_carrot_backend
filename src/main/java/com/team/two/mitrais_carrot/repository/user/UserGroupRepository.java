package com.team.two.mitrais_carrot.repository.user;

import com.team.two.mitrais_carrot.entity.group.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Integer> {
    //List<UserGroupEntity> findByGroupId(int groupId);

    List<UserGroupEntity> findByGroup_Id(int id);

    UserGroupEntity findByGroup_IdEquals(Integer id);

    UserGroupEntity findByUser_Id(Long id);

}
