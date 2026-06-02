package com.ecommerce.inventory.controller;

import com.ecommerce.common.dto.Result;
import com.ecommerce.inventory.entity.Inventory;
import com.ecommerce.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Inventory Controller
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * Get inventory by product ID
     */
    @GetMapping("/{productId}")
    public Result<Integer> getInventory(@PathVariable Long productId) {
        Integer quantity = inventoryService.getAvailableQuantity(productId);
        return Result.success(quantity);
    }

    /**
     * Get inventory details
     */
    @GetMapping("/details/{productId}")
    public Result<Inventory> getInventoryDetails(@PathVariable Long productId) {
        Inventory inventory = inventoryService.findByProductId(productId);
        return Result.success(inventory);
    }

    /**
     * Deduct inventory (called by order-service via Feign with Seata)
     */
    @GetMapping("/deduct/{productId}/{quantity}")
    public Result<Boolean> deductInventory(@PathVariable Long productId, @PathVariable Integer quantity) {
        boolean result = inventoryService.deductInventory(productId, quantity);
        return Result.success(result);
    }

    /**
     * Restore inventory when order is cancelled
     */
    @PostMapping("/restore/{productId}/{quantity}")
    public Result<Boolean> restoreInventory(@PathVariable Long productId, @PathVariable Integer quantity) {
        boolean result = inventoryService.restoreInventory(productId, quantity);
        return Result.success(result);
    }

    /**
     * Create or update inventory
     */
    @PostMapping
    public Result<Inventory> createOrUpdateInventory(@RequestBody Inventory inventory) {
        inventoryService.saveOrUpdate(inventory);
        return Result.success(inventory);
    }
}
