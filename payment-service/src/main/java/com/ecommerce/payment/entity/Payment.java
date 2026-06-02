package com.ecommerce.payment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ecommerce.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Payment Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("payment_info")
public class Payment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private BigDecimal amount;
    private Integer paymentMethod; // 0: credit card, 1: debit card, 2: wallet
    private Integer status; // 0: pending, 1: success, 2: failed
    private String transactionNo;
    private String remarks;
}
