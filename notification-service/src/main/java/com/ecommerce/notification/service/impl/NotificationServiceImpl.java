package com.ecommerce.notification.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.notification.entity.Notification;
import com.ecommerce.notification.mapper.NotificationMapper;
import com.ecommerce.notification.service.NotificationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Notification Service Implementation
 */
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {

    @Override
    public Notification findByUserId(Long userId) {
        return baseMapper.findByUserId(userId);
    }

    @Override
    public boolean sendEmail(Long userId, String subject, String content) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setType("EMAIL");
        notification.setTitle(subject);
        notification.setContent(content);
        notification.setStatus(0); // pending
        notification.setCreateTime(LocalDateTime.now());
        notification.setUpdateTime(LocalDateTime.now());

        try {
            // In real scenario, integrate with email service (e.g., SendGrid, AWS SES)
            Thread.sleep(100); // Simulate sending
            notification.setStatus(1); // sent
            save(notification);
            return true;
        } catch (Exception e) {
            notification.setStatus(2); // failed
            notification.setRemarks(e.getMessage());
            save(notification);
            throw new BusinessException("Failed to send email: " + e.getMessage());
        }
    }

    @Override
    public boolean sendSMS(Long userId, String content) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setType("SMS");
        notification.setTitle("SMS Notification");
        notification.setContent(content);
        notification.setStatus(0); // pending
        notification.setCreateTime(LocalDateTime.now());
        notification.setUpdateTime(LocalDateTime.now());

        try {
            // In real scenario, integrate with SMS service (e.g., Twilio, Alibaba Cloud SMS)
            Thread.sleep(100); // Simulate sending
            notification.setStatus(1); // sent
            save(notification);
            return true;
        } catch (Exception e) {
            notification.setStatus(2); // failed
            notification.setRemarks(e.getMessage());
            save(notification);
            throw new BusinessException("Failed to send SMS: " + e.getMessage());
        }
    }

    @Override
    public boolean sendPushNotification(Long userId, String title, String content) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setType("PUSH");
        notification.setTitle(title);
        notification.setContent(content);
        notification.setStatus(0); // pending
        notification.setCreateTime(LocalDateTime.now());
        notification.setUpdateTime(LocalDateTime.now());

        try {
            // In real scenario, integrate with push notification service (e.g., Firebase, JPush)
            Thread.sleep(100); // Simulate sending
            notification.setStatus(1); // sent
            save(notification);
            return true;
        } catch (Exception e) {
            notification.setStatus(2); // failed
            notification.setRemarks(e.getMessage());
            save(notification);
            throw new BusinessException("Failed to send push notification: " + e.getMessage());
        }
    }
}
