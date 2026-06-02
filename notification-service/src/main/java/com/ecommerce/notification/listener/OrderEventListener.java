package com.ecommerce.notification.listener;

import com.ecommerce.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import com.ecommerce.notification.stream.NotificationSink;

/**
 * Order Event Listener - Listen to order events from RabbitMQ
 */
@Component
public class OrderEventListener {

    @Autowired
    private NotificationService notificationService;

    @StreamListener(NotificationSink.INPUT)
    public void handleOrderEvent(String orderEvent) {
        try {
            // Parse order event and send notification
            // Example: {"userId": 1, "orderId": 100, "type": "ORDER_PLACED"}
            notificationService.sendEmail(1L, "Order Confirmation", "Your order has been placed successfully.");
            notificationService.sendPushNotification(1L, "Order Confirmation", "Your order has been placed successfully.");
        } catch (Exception e) {
            // Log error and continue processing
            System.err.println("Error processing order event: " + e.getMessage());
        }
    }
}
