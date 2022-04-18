package com.team.two.mitrais_carrot.dto.notification;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long receiverId;
    private String message;
}
