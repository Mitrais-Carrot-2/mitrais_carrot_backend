package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.userGroup.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Integer> {
}
