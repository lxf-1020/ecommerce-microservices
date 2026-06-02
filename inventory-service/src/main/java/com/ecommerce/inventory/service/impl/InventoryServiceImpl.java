package com.ecommerce.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.inventory.entity.Inventory;
import com.ecommerce.inventory.entity.InventoryChangeLog;
import com.ecommerce.inventory.mapper.InventoryChangeLogMapper;
import com.ecommerce.inventory.mapper.InventoryMapper;
import com.ecommerce.inventory.service.InventoryService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Inventory Service Implementation
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Autowired
    private InventoryChangeLogMapper changeLogMapper;

    @Override
    public Inventory findByProductId(Long productId) {
        return baseMapper.findByProductId(productId);
    }

    @Override
    public Integer getAvailableQuantity(Long productId) {
        Inventory inventory = findByProductId(productId);
        if (inventory == null) {
            return 0;
        }
        // Available = Total - Reserved
        return inventory.getQuantity() - (inventory.getReservedQuantity() != null ? inventory.getReservedQuantity() : 0);
    }

    /**
     * Deduct inventory with Seata distributed transaction support
     */
    @Override
    @GlobalTransactional(name = "deductInventoryTx", rollbackFor = Exception.class)
    public boolean deductInventory(Long productId, Integer quantity) {
        Inventory inventory = findByProductId(productId);
        if (inventory == null) {
            throw new BusinessException("Product not found in inventory");
        }

        Integer available = getAvailableQuantity(productId);
        if (available < quantity) {
            throw new BusinessException("Insufficient inventory. Available: " + available + ", Requested: " + quantity);
        }

        // Deduct inventory with row-level lock
        int result = baseMapper.deductInventory(productId, quantity);
        if (result <= 0) {
            throw new BusinessException("Failed to deduct inventory");
        }

        // Record change log
        InventoryChangeLog log = new InventoryChangeLog();
        log.setProductId(productId);
        log.setQuantity(-quantity);
        log.setType("DEDUCT");
        log.setCreateTime(LocalDateTime.now());
        log.setUpdateTime(LocalDateTime.now());
        changeLogMapper.insert(log);

        return true;
    }

    /**
     * Restore inventory when order is cancelled
     */
    @Override
    public boolean restoreInventory(Long productId, Integer quantity) {
        Inventory inventory = findByProductId(productId);
        if (inventory == null) {
            throw new BusinessException("Product not found in inventory");
        }

        // Restore inventory
        int result = baseMapper.restoreInventory(productId, quantity);
        if (result <= 0) {
            throw new BusinessException("Failed to restore inventory");
        }

        // Record change log
        InventoryChangeLog log = new InventoryChangeLog();
        log.setProductId(productId);
        log.setQuantity(quantity);
        log.setType("RESTORE");
        log.setCreateTime(LocalDateTime.now());
        log.setUpdateTime(LocalDateTime.now());
        changeLogMapper.insert(log);

        return true;
    }
}
