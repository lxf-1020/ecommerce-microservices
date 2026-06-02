package com.ecommerce.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.mapper.PaymentMapper;
import com.ecommerce.payment.service.PaymentService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Payment Service Implementation
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    @Override
    public Payment findByOrderId(Long orderId) {
        return baseMapper.findByOrderId(orderId);
    }

    @Override
    public Payment findByTransactionNo(String transactionNo) {
        return baseMapper.findByTransactionNo(transactionNo);
    }

    /**
     * Process payment with Seata support
     */
    @Override
    @GlobalTransactional(name = "paymentTx", rollbackFor = Exception.class)
    public boolean processPayment(Long orderId, BigDecimal amount) {
        // Get Seata XID for distributed transaction
        String xid = RootContext.getXID();
        
        // Create payment record
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setTransactionNo(UUID.randomUUID().toString().replace("-", ""));
        payment.setPaymentMethod(0); // default: credit card
        payment.setStatus(0); // pending
        payment.setCreateTime(LocalDateTime.now());
        payment.setUpdateTime(LocalDateTime.now());
        payment.setRemarks("XID: " + xid);

        // Simulate payment processing
        try {
            // In real scenario, integrate with payment gateway
            Thread.sleep(100); // Simulate network latency
            
            // Update payment status to success
            payment.setStatus(1); // success
            save(payment);
            return true;
        } catch (Exception e) {
            payment.setStatus(2); // failed
            save(payment);
            throw new BusinessException("Payment processing failed: " + e.getMessage());
        }
    }

    @Override
    public boolean refundPayment(Long paymentId) {
        Payment payment = baseMapper.selectById(paymentId);
        if (payment == null) {
            throw new BusinessException("Payment not found");
        }
        if (payment.getStatus() != 1) {
            throw new BusinessException("Only successful payments can be refunded");
        }
        payment.setStatus(3); // refunded
        return updateById(payment);
    }
}
