package com.ecommerce.notification.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.notification.entity.Notification;

/**
 * Notification Service Interface
 */
public interface NotificationService extends IService<Notification> {
    Notification findByUserId(Long userId);
    boolean sendEmail(Long userId, String subject, String content);
    boolean sendSMS(Long userId, String content);
    boolean sendPushNotification(Long userId, String title, String content);
}
