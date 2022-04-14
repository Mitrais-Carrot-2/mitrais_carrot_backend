package com.team.two.mitrais_carrot.dto.notification;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
public class NotificationDto {
    private String receiverId;
    private String message;
}
