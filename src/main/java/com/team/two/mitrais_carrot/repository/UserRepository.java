package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    List<UserEntity> findAllByDayOfYearBirthDay(int dayOfYear);
}
