package com.team.two.mitrais_carrot.service.notification;


import com.team.two.mitrais_carrot.dto.notification.NotificationDto;
import com.team.two.mitrais_carrot.entity.auth.UserEntity;
import com.team.two.mitrais_carrot.entity.notification.NotificationEntity;
import com.team.two.mitrais_carrot.repository.NotificationRepository;
import com.team.two.mitrais_carrot.security.services.UserDetailsImpl;
import com.team.two.mitrais_carrot.service.user.ManagerService;
import com.team.two.mitrais_carrot.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public Long getUserIdAuth() {
        Long userId = 1L;
        try {
            UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userId = user.getId();
            logger.info("User ID: "+userId);
        } catch (ClassCastException err) {
            logger.error("No Authorization / Supervisor not exist!");
        }

        return userId;
    }

    public NotificationEntity createNotification(NotificationDto notificationDto) {
        NotificationEntity notification = new NotificationEntity();
//        notification.setDate(LocalDate.now());
//        notification.setRead(false);
        notification.setMessage(notificationDto.getMessage());
        notification.setReceiverId(notificationDto.getReceiverId());
        logger.info("Notification created");
        logger.info(notification.toString());

        try {
            sendEmail(notification);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return notificationRepository.save(notification);
    }

    public List<NotificationEntity> getNotification() {

        return notificationRepository.findAllByReceiverId(getUserIdAuth());
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

    public boolean readAllNotification() {
//        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long userId = user.getId();
        logger.error("User ID read ALL: "+getUserIdAuth());
        List<NotificationEntity> notification = notificationRepository.findByReceiverIdAndIsRead(getUserIdAuth(), false);
        if(notification != null) {
            notification.forEach(notificationEntity -> {
                logger.error("Notification id: " + notificationEntity.getId());
                notificationEntity.setRead(true);
                notificationRepository.save(notificationEntity);
            });
            return true;
        } else {
            return false;
        }
    }

    public void sendEmail(NotificationEntity notificationDto) throws MessagingException, UnsupportedEncodingException {
        //get user by id from notificationDto.getReceiverId()
        //get user email from user.getEmail()
        UserEntity receiver = userService.getById(notificationDto.getReceiverId());
        String receiverEmail = receiver.getEmail();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);
        mailSender.setUsername("255ea3d902defc");
        mailSender.setPassword("072531824f9fb4");

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);

        mailSender.setJavaMailProperties(mailProperties);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("carrotNotif@mitrais.com", "Carrot Notification");
        helper.setTo(receiverEmail);
        helper.setSubject("Carrot Notification");

        String content = notificationDto.getMessage();
        helper.setText(content, true);

        mailSender.send(message);
    }
}
