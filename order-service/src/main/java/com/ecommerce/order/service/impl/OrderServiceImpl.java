package com.ecommerce.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.order.feign.InventoryClient;
import com.ecommerce.order.feign.PaymentClient;
import com.ecommerce.order.mapper.OrderItemMapper;
import com.ecommerce.order.mapper.OrderMapper;
import com.ecommerce.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Order Service Implementation
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private PaymentClient paymentClient;

    @Override
    public List<Order> findByUserId(Long userId) {
        return baseMapper.findByUserId(userId);
    }

    @Override
    public Order findByOrderNo(String orderNo) {
        return baseMapper.findByOrderNo(orderNo);
    }

    /**
     * Create order with Seata distributed transaction
     * This ensures atomicity across multiple services
     */
    @Override
    @GlobalTransactional(name = "createOrderTx", rollbackFor = Exception.class)
    public Order createOrder(Long userId, List<OrderItem> items, String shippingAddress) {
        // 1. Create order
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        order.setShippingAddress(shippingAddress);
        order.setStatus(0); // pending
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        BigDecimal totalAmount = BigDecimal.ZERO;

        // 2. Check inventory and deduct (via Seata)
        for (OrderItem item : items) {
            Integer inventory = inventoryClient.getInventory(item.getProductId()).getData();
            if (inventory == null || inventory < item.getQuantity()) {
                throw new BusinessException("Insufficient inventory for product: " + item.getProductId());
            }
            // Deduct inventory through feign client (Seata manages transaction)
            Boolean deductResult = inventoryClient.deductInventory(item.getProductId(), item.getQuantity()).getData();
            if (!deductResult) {
                throw new BusinessException("Failed to deduct inventory");
            }
            totalAmount = totalAmount.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        order.setTotalAmount(totalAmount);
        save(order);

        // 3. Save order items
        for (OrderItem item : items) {
            item.setOrderId(order.getId());
            orderItemMapper.insert(item);
        }

        // 4. Process payment
        Boolean paymentResult = paymentClient.processPayment(order.getId(), totalAmount).getData();
        if (paymentResult) {
            order.setStatus(1); // paid
            updateById(order);
        }

        return order;
    }

    @Override
    public boolean cancelOrder(Long orderId) {
        Order order = baseMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException("Order not found");
        }
        if (order.getStatus() != 0) {
            throw new BusinessException("Order cannot be cancelled");
        }
        order.setStatus(4); // cancelled
        return updateById(order);
    }

    @Override
    public Order getOrderDetail(Long orderId) {
        return baseMapper.selectById(orderId);
    }
}
