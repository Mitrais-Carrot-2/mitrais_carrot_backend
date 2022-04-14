package com.team.two.mitrais_carrot.controller.notification;


import com.team.two.mitrais_carrot.entity.notification.NotificationEntity;
import com.team.two.mitrais_carrot.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping("{userId}")
    public List<NotificationEntity> getNotification(@PathVariable("userId") Long userId){
        return notificationService.getNotification(userId);
    }

    @PutMapping("read/{notificationId}")
    public void readNotification(@PathVariable("notificationId") Long notificationId){
        notificationService.readNotification(notificationId);
    }

}
