package com.team.two.mitrais_carrot.service.notification;


import com.team.two.mitrais_carrot.dto.notification.NotificationDto;
import com.team.two.mitrais_carrot.entity.notification.NotificationEntity;
import com.team.two.mitrais_carrot.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(NotificationDto notificationDto) {
        NotificationEntity notification = new NotificationEntity();
        notification.setDate(LocalDate.now());
        notification.setRead(false);
        notification.setMessage(notificationDto.getMessage());
        notification.setReceiverId(notificationDto.getReceiverId());
        notificationRepository.save(notification);
    }

    public List<NotificationEntity> getNotification(Long userId) {
        return (List<NotificationEntity>) notificationRepository.findAllByReceiverId(userId);
    }

    public void readNotification(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId).get();
        notification.setRead(true);
        notificationRepository.save(notification);
    }

}
