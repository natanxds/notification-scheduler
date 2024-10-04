package com.natanxds.Microservice.service;

import com.natanxds.Microservice.dto.ScheduleNotificationDto;
import com.natanxds.Microservice.entity.Notification;
import com.natanxds.Microservice.entity.Status;
import com.natanxds.Microservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;

    }

    public void scheduleNotification(ScheduleNotificationDto scheduleNotificationDto) {
        notificationRepository.save(scheduleNotificationDto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }

    public void cancelNotification(Long notificationId) {
        var notification = notificationRepository.findById(notificationId);
        if (notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            notificationRepository.save(notification.get());
        }
    }

    public void checkAndSend(LocalDateTime localDateTime) {
        List<Notification> pedingAndErrorsNotifications = notificationRepository.findByStatusInAndDateTimeBefore(List.of(
                Status.Values.PEDING.toStatus(),
                Status.Values.ERROR.toStatus()
        ), localDateTime);

        pedingAndErrorsNotifications.forEach(sendNotification());
    }

    private Consumer<Notification> sendNotification() {
        return notification -> {
            try {
                // send notification
                notification.setStatus(Status.Values.SUCCESS.toStatus());
            } catch (Exception e) {
                notification.setStatus(Status.Values.ERROR.toStatus());
            }
            notificationRepository.save(notification);
        };
    }
}

