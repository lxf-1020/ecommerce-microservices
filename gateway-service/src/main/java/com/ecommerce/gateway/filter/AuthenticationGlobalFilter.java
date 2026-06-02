package com.ecommerce.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Global Authentication Filter
 */
@Component
public class AuthenticationGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        
        // Skip authentication for certain endpoints
        if (path.startsWith("/api/auth/") || path.startsWith("/api/products/")) {
            return chain.filter(exchange);
        }

        // Get token from header
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        
        // Validate token
        if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Add user info to request attributes for downstream services
        // In real scenario, validate JWT token here
        exchange.getAttributes().put("userId", "1"); // Mock user ID
        
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100; // Execute before other filters
    }
}
