package com.ecommerce.notification.controller;

import com.ecommerce.common.dto.Result;
import com.ecommerce.notification.entity.Notification;
import com.ecommerce.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Notification Controller
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Get notification by user ID
     */
    @GetMapping("/user/{userId}")
    public Result<Notification> getNotificationByUserId(@PathVariable Long userId) {
        Notification notification = notificationService.findByUserId(userId);
        return Result.success(notification);
    }

    /**
     * Send email notification
     */
    @PostMapping("/email")
    public Result<Boolean> sendEmail(@RequestParam Long userId, @RequestParam String subject, @RequestParam String content) {
        boolean result = notificationService.sendEmail(userId, subject, content);
        return Result.success(result);
    }

    /**
     * Send SMS notification
     */
    @PostMapping("/sms")
    public Result<Boolean> sendSMS(@RequestParam Long userId, @RequestParam String content) {
        boolean result = notificationService.sendSMS(userId, content);
        return Result.success(result);
    }

    /**
     * Send push notification
     */
    @PostMapping("/push")
    public Result<Boolean> sendPushNotification(@RequestParam Long userId, @RequestParam String title, @RequestParam String content) {
        boolean result = notificationService.sendPushNotification(userId, title, content);
        return Result.success(result);
    }

    /**
     * Create notification
     */
    @PostMapping
    public Result<Notification> createNotification(@RequestBody Notification notification) {
        notificationService.save(notification);
        return Result.success(notification);
    }
}
