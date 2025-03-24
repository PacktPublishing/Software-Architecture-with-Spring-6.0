package com.packtpub.notificationservices.adapter.datasources.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDocument {

    @Id
    private String id;
    private String auction;
    private List<String> emails;

}
