package com.team.two.mitrais_carrot.entity.notification;


import lombok.*;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id = UUID.randomUUID();

    @Column(name = "is_read")
    private boolean isRead = false;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "message")
    private String message;
}
