package com.ecommerce.order.feign;

import com.ecommerce.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Inventory Service Feign Client
 */
@FeignClient(name = "inventory-service")
public interface InventoryClient {

    /**
     * Check inventory
     */
    @GetMapping("/api/inventory/{productId}")
    Result<Integer> getInventory(@PathVariable Long productId);

    /**
     * Deduct inventory (for Seata distributed transaction)
     */
    @GetMapping("/api/inventory/deduct/{productId}/{quantity}")
    Result<Boolean> deductInventory(@PathVariable Long productId, @PathVariable Integer quantity);
}
