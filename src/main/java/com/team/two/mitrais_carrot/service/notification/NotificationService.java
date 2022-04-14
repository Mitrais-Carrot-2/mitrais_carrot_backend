package com.team.two.mitrais_carrot.service.notification;


import com.team.two.mitrais_carrot.dto.notification.NotificationDto;
import com.team.two.mitrais_carrot.entity.notification.NotificationEntity;
import com.team.two.mitrais_carrot.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(NotificationDto notificationDto) {
        NotificationEntity notification = new NotificationEntity();
        notification.setDate(LocalDate.now());
        notification.setMessage(notificationDto.getMessage());
        notification.setReceiverId(notificationDto.getReceiverId());
        notificationRepository.save(notification);
    }

    public String getNotification(Long userId) {
        return notificationRepository.findByReceiverId(userId).toString();
    }


}
