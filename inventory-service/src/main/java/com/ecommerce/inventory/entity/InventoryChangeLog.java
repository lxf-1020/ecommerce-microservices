package com.ecommerce.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ecommerce.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Inventory Change Log Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("inventory_change_log")
public class InventoryChangeLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long productId;
    private Integer quantity;
    private String type; // DEDUCT, RESTORE, RETURN
    private Long orderId;
    private String remarks;
}
