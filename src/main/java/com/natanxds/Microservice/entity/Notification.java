package com.natanxds.Microservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notification")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private LocalDateTime dateTime;

    private String destination;

    private String message;

    @ManyToOne
    @JoinColumn(name = "channelId")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "statusId")
    private Status status;

    public Notification(LocalDateTime dateTime, String destinaton, String message, Channel channel) {
        this.dateTime = dateTime;
        this.destination = destinaton;
        this.message = message;
        this.channel = channel;
        this.status = Status.Values.PEDING.toStatus();
    }
}
