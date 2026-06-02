package com.ecommerce.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.inventory.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Inventory Mapper
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
    Inventory findByProductId(Long productId);
    
    /**
     * Deduct inventory (row-level lock for concurrency)
     */
    int deductInventory(@Param("productId") Long productId, @Param("quantity") Integer quantity);
    
    /**
     * Restore inventory when order is cancelled
     */
    int restoreInventory(@Param("productId") Long productId, @Param("quantity") Integer quantity);
}
