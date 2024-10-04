package com.natanxds.Microservice.dto;

import com.natanxds.Microservice.entity.Channel;
import com.natanxds.Microservice.entity.Notification;

import java.time.LocalDateTime;

public record ScheduleNotificationDto(LocalDateTime dateTime,
                                      String destination,
                                      String message,
                                      Channel.Values channel) {
    public Notification toNotification() {
        return new Notification(dateTime, destination, message, channel.toChannel());
    }
}
