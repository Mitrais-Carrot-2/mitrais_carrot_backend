package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.auth.ERole;
import com.team.two.mitrais_carrot.entity.auth.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
  Optional<RoleEntity> findByName(ERole name);
}
