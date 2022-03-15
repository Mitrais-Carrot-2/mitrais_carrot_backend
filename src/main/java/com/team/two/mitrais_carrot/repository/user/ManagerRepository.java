package com.team.two.mitrais_carrot.repository.user;

import com.team.two.mitrais_carrot.dto.user.StaffDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends JpaRepository<UserEntity, Integer> {
//    @Query("SELECT user.id, user.username, user.firstName, user.lastName, user.jobFamily, user.jobGrade FROM UserEntity user WHERE user.supervisorId = :managerId")
//    List<UserEntity> findBySupervisorId(@Param("managerId") Long managerId);

    List<UserEntity> findBySupervisorId(Long managerId);

}
