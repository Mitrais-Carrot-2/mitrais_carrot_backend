package com.team.two.mitrais_carrot.controller.notification;


import com.team.two.mitrais_carrot.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Object getNotification(Long userId){
        return notificationService.getNotification(userId);
    }
}
