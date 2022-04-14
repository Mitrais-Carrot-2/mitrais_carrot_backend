package com.team.two.mitrais_carrot.entity.notification;


import lombok.*;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "receiver_id")
    private String receiverId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "message")
    private String message;
}
