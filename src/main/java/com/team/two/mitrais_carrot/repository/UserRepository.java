package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<UserEntity> findAllByDayOfYearBirthDay(int dayOfYear);

    // @Query("select e from users e where day(e.birthDate) = ?1 and month(e.birthDate) = ?2")
    // List<UserEntity> getByDayAndMonth(int day, int month);

}
