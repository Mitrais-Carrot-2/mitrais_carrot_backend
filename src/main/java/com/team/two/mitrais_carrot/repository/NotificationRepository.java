package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.controller.notification.NotificationController;
import com.team.two.mitrais_carrot.entity.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByReceiverId(Long id);

    NotificationEntity findById(UUID id);
}
