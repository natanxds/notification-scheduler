package com.natanxds.Microservice.repository;

import com.natanxds.Microservice.entity.Notification;
import com.natanxds.Microservice.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime localDateTime);
}
