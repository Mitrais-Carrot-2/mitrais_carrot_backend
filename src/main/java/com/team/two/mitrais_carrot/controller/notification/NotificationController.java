package com.team.two.mitrais_carrot.controller.notification;


import com.team.two.mitrais_carrot.dto.MessageDto;
import com.team.two.mitrais_carrot.dto.notification.NotificationDto;
import com.team.two.mitrais_carrot.entity.notification.NotificationEntity;
import com.team.two.mitrais_carrot.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasAnyRole('STAFF')")
    @GetMapping("/")
    public List<NotificationEntity> getNotification(){
        return notificationService.getNotification();
    }

    @PostMapping("/")
    public NotificationEntity pushNotification(@RequestBody NotificationDto req){
        return notificationService.createNotification(req);
    }

    @PutMapping("/read/{notificationId}")
    public ResponseEntity<MessageDto> readNotification(@PathVariable("notificationId") UUID notificationId){
        Boolean status = notificationService.readNotification(notificationId);
        if (!status){
            return ResponseEntity.badRequest().body(new MessageDto("Notification not found!", false));
        } else {
            return ResponseEntity.ok().body(new MessageDto("Notification mark as read!", true));
        }
    }

}
