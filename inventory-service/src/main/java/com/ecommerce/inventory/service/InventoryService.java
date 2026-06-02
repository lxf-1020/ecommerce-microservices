package com.ecommerce.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.inventory.entity.Inventory;

/**
 * Inventory Service Interface
 */
public interface InventoryService extends IService<Inventory> {
    Inventory findByProductId(Long productId);
    Integer getAvailableQuantity(Long productId);
    boolean deductInventory(Long productId, Integer quantity);
    boolean restoreInventory(Long productId, Integer quantity);
}
