package com.ecommerce.notification.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Notification Sink - Listen to order events
 */
public interface NotificationSink {
    String INPUT = "orderNotification";

    @Input(INPUT)
    SubscribableChannel orderNotification();
}
