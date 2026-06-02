package com.ecommerce.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.inventory.entity.InventoryChangeLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Inventory Change Log Mapper
 */
@Mapper
public interface InventoryChangeLogMapper extends BaseMapper<InventoryChangeLog> {
}
