package com.example.Notifi.service;

import com.example.Notifi.model.Notification;
import com.example.Notifi.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // Create & save a new notification
    public Notification sendNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    // Fetch all notifications for a receiver (student/vendor)
    public List<Notification> getNotificationsForUser(Long receiverId) {
        return notificationRepository.findByReceiverId(receiverId);
    }

    // Optional: fetch all notifications sent by a vendor/admin
    public List<Notification> getNotificationsFromSender(Long senderId) {
        return notificationRepository.findBySenderId(senderId);
    }
}
