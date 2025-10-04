package com.example.Notifi.repository;

import com.example.Notifi.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Custom finder: get all notifications for a given receiver (student/vendor)
    List<Notification> findByReceiverId(Long receiverId);

    // Optional: get all notifications sent by a vendor/admin
    List<Notification> findBySenderId(Long senderId);
}
