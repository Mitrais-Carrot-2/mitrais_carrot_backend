package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.auth.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    UserRoleEntity findByRoleId(Integer roleId);
}
