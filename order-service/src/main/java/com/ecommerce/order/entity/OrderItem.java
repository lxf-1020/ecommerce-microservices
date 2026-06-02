package com.ecommerce.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ecommerce.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Order Item Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("order_item")
public class OrderItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private String productName;
}
