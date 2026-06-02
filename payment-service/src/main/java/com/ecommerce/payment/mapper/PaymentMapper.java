package com.ecommerce.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.payment.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * Payment Mapper
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
    Payment findByOrderId(Long orderId);
    Payment findByTransactionNo(String transactionNo);
}
