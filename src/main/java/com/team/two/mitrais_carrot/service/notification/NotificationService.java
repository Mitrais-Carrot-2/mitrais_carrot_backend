package com.team.two.mitrais_carrot.service.notification;


import com.team.two.mitrais_carrot.dto.notification.NotificationDto;
import com.team.two.mitrais_carrot.entity.notification.NotificationEntity;
import com.team.two.mitrais_carrot.repository.NotificationRepository;
import com.team.two.mitrais_carrot.security.services.UserDetailsImpl;
import com.team.two.mitrais_carrot.service.user.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public Long getUserIdAuth() {
        Long userId = 1L;
        try {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userId = user.getId();
            logger.info("User ID: "+userId);
        } catch (ClassCastException err) {
            logger.error("No Authorization / Supervisor not exist!");
        }

        return userId;
    }

    public NotificationEntity createNotification(NotificationDto notificationDto) {
        NotificationEntity notification = new NotificationEntity();
//        notification.setDate(LocalDate.now());
//        notification.setRead(false);
        notification.setMessage(notificationDto.getMessage());
        notification.setReceiverId(notificationDto.getReceiverId());
        logger.info("Notification created");
        logger.info(notification.toString());
        return notificationRepository.save(notification);
    }

    public List<NotificationEntity> getNotification() {

        return notificationRepository.findAllByReceiverId(getUserIdAuth());
//        return notificationRepository.findAll();
    }

    public boolean readNotification(UUID notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId);
        if(notification != null) {
            notification.setRead(true);
            notificationRepository.save(notification);
            return true;
        } else {
            return false;
        }
    }

    public boolean readAllNotification() {
//        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long userId = user.getId();
        logger.error("User ID read ALL: "+getUserIdAuth());
        List<NotificationEntity> notification = notificationRepository.findByReceiverIdAndIsRead(getUserIdAuth(), false);
        if(notification != null) {
            notification.forEach(notificationEntity -> {
                logger.error("Notification id: " + notificationEntity.getId());
                notificationEntity.setRead(true);
                notificationRepository.save(notificationEntity);
            });
            return true;
        } else {
            return false;
        }
    }

}
