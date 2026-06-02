package com.ecommerce.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ecommerce.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Inventory Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("inventory_info")
public class Inventory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long productId;
    private Integer quantity;
    private Integer reservedQuantity; // Reserved quantity for pending orders
    private Integer warehouseId;
}
