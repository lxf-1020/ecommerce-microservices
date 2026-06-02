package com.ecommerce.order.feign;

import com.ecommerce.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Payment Service Feign Client
 */
@FeignClient(name = "payment-service")
public interface PaymentClient {

    /**
     * Process payment
     */
    @GetMapping("/api/payments/process/{orderId}/{amount}")
    Result<Boolean> processPayment(@PathVariable Long orderId, @PathVariable java.math.BigDecimal amount);
}
