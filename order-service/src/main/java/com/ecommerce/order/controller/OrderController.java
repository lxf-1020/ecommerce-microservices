package com.ecommerce.order.controller;

import com.ecommerce.common.dto.Result;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Order Controller
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Get orders by user ID
     */
    @GetMapping("/user/{userId}")
    public Result<List<Order>> getUserOrders(@PathVariable Long userId) {
        List<Order> orders = orderService.findByUserId(userId);
        return Result.success(orders);
    }

    /**
     * Get order detail
     */
    @GetMapping("/{orderId}")
    public Result<Order> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderDetail(orderId);
        return Result.success(order);
    }

    /**
     * Get order by order number
     */
    @GetMapping("/no/{orderNo}")
    public Result<Order> getOrderByNo(@PathVariable String orderNo) {
        Order order = orderService.findByOrderNo(orderNo);
        return Result.success(order);
    }

    /**
     * Create order with distributed transaction (Seata)
     */
    @PostMapping("/create")
    public Result<Order> createOrder(@RequestParam Long userId,
                                      @RequestParam String shippingAddress,
                                      @RequestBody List<OrderItem> items) {
        Order order = orderService.createOrder(userId, items, shippingAddress);
        return Result.success(order);
    }

    /**
     * Cancel order
     */
    @PutMapping("/{orderId}/cancel")
    public Result<Boolean> cancelOrder(@PathVariable Long orderId) {
        boolean result = orderService.cancelOrder(orderId);
        return Result.success(result);
    }

    /**
     * Update order status
     */
    @PutMapping("/{orderId}/status")
    public Result<Boolean> updateStatus(@PathVariable Long orderId, @RequestParam Integer status) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(status);
        boolean result = orderService.updateById(order);
        return Result.success(result);
    }
}
