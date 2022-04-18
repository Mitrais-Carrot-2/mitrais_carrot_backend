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
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        return notificationRepository.findAllByReceiverId(userId);
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

}
