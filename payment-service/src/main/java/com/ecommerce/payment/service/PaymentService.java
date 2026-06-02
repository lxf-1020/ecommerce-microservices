package com.ecommerce.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.payment.entity.Payment;
import java.math.BigDecimal;

/**
 * Payment Service Interface
 */
public interface PaymentService extends IService<Payment> {
    Payment findByOrderId(Long orderId);
    Payment findByTransactionNo(String transactionNo);
    boolean processPayment(Long orderId, BigDecimal amount);
    boolean refundPayment(Long paymentId);
}
