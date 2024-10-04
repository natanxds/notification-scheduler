package com.natanxds.Microservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_channel")
@NoArgsConstructor
@Data
public class Channel {

    @Id
    private Long channelId;

    private String description;

    public Channel(Long channelId, String description) {
        this.channelId = channelId;
        this.description = description;
    }

    public enum Values {
        EMAIL(1L, "Email"),
        SMS(2L, "SMS"),
        PUSH(3L, "Push"),
        WHATSAPP(4L, "WhatsApp");

        private Long id;
        private String description;

        Values(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public Channel toChannel() {
            return new Channel(id, description);
        }
    }
}
