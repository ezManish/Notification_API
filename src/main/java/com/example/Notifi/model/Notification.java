package com.example.Notifi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;     // vendor/admin userId
    private Long receiverId;   // student userId
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type; // ORDER_CONFIRMED, READY_TO_PICKUP, etc.

    private LocalDateTime timestamp;
}
