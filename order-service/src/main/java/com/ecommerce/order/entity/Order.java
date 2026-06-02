package com.ecommerce.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ecommerce.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Order Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("order_info")
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String orderNo;
    private BigDecimal totalAmount;
    private Integer status; // 0: pending, 1: paid, 2: shipped, 3: delivered, 4: cancelled
    private String shippingAddress;
    private String remarks;
}
