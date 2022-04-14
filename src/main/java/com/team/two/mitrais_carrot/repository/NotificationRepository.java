package com.team.two.mitrais_carrot.repository;

import com.team.two.mitrais_carrot.controller.notification.NotificationController;
import com.team.two.mitrais_carrot.entity.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    NotificationEntity findAllByReceiverId(Long id);

}
