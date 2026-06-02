package com.ecommerce.payment.controller;

import com.ecommerce.common.dto.Result;
import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Payment Controller
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Get payment by order ID
     */
    @GetMapping("/order/{orderId}")
    public Result<Payment> getPaymentByOrderId(@PathVariable Long orderId) {
        Payment payment = paymentService.findByOrderId(orderId);
        return Result.success(payment);
    }

    /**
     * Get payment by transaction number
     */
    @GetMapping("/transaction/{transactionNo}")
    public Result<Payment> getPaymentByTransactionNo(@PathVariable String transactionNo) {
        Payment payment = paymentService.findByTransactionNo(transactionNo);
        return Result.success(payment);
    }

    /**
     * Get payment by ID
     */
    @GetMapping("/{id}")
    public Result<Payment> getPayment(@PathVariable Long id) {
        Payment payment = paymentService.getById(id);
        return Result.success(payment);
    }

    /**
     * Process payment (called by order-service via Feign with Seata)
     */
    @GetMapping("/process/{orderId}/{amount}")
    public Result<Boolean> processPayment(@PathVariable Long orderId, @PathVariable BigDecimal amount) {
        boolean result = paymentService.processPayment(orderId, amount);
        return Result.success(result);
    }

    /**
     * Refund payment
     */
    @PostMapping("/refund/{paymentId}")
    public Result<Boolean> refundPayment(@PathVariable Long paymentId) {
        boolean result = paymentService.refundPayment(paymentId);
        return Result.success(result);
    }
}
