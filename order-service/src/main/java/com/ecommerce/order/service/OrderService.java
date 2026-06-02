package com.ecommerce.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import java.util.List;

/**
 * Order Service Interface
 */
public interface OrderService extends IService<Order> {
    List<Order> findByUserId(Long userId);
    Order findByOrderNo(String orderNo);
    Order createOrder(Long userId, List<OrderItem> items, String shippingAddress);
    boolean cancelOrder(Long orderId);
    Order getOrderDetail(Long orderId);
}
