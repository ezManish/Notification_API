package com.example.Notifi.controller;

import com.example.Notifi.model.Notification;
import com.example.Notifi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // ✅ API 1: Send a notification
    @PostMapping
    public ResponseEntity<Notification> sendNotification(@RequestBody Notification notification) {
        Notification saved = notificationService.sendNotification(notification);
        return ResponseEntity.ok(saved);
    }

    // ✅ API 2: Get notifications for a user (student/vendor)
    @GetMapping("/{receiverId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long receiverId) {
        List<Notification> notifications = notificationService.getNotificationsForUser(receiverId);
        return ResponseEntity.ok(notifications);
    }

    // ✅ API 3: Mark notification as read
    @PatchMapping("/{id}/read")
    public ResponseEntity<Notification> markNotificationRead(@PathVariable Long id) {
        Notification updated = notificationService.markNotificationRead(id);
        return ResponseEntity.ok(updated);
    }

    // ✅ API 4: Delete a notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ API 5: Clear all notifications for a user
    @DeleteMapping("/user/{userId}/all")
    public ResponseEntity<Void> clearAllNotifications(@PathVariable Long userId) {
        notificationService.clearAllNotifications(userId);
        return ResponseEntity.noContent().build();
    }
}
