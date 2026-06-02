package com.ecommerce.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecommerce.common.dto.Result;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Product Controller
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Get product by ID
     */
    @GetMapping("/{id}")
    public Result<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProductDetail(id);
        return Result.success(product);
    }

    /**
     * Get products by category
     */
    @GetMapping("/category/{categoryId}")
    public Result<List<Product>> getByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.findByCategory(categoryId);
        return Result.success(products);
    }

    /**
     * Search products by keyword
     */
    @GetMapping("/search")
    public Result<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchByName(keyword);
        return Result.success(products);
    }

    /**
     * Get all products with pagination
     */
    @GetMapping("/list")
    public Result<Page<Product>> listProducts(@RequestParam(defaultValue = "1") Long page,
                                               @RequestParam(defaultValue = "10") Long pageSize) {
        Page<Product> productPage = productService.page(new Page<>(page, pageSize));
        return Result.success(productPage);
    }

    /**
     * Create product
     */
    @PostMapping
    public Result<Product> createProduct(@RequestBody Product product) {
        productService.save(product);
        return Result.success(product);
    }

    /**
     * Update product
     */
    @PutMapping("/{id}")
    public Result<Boolean> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        boolean result = productService.updateById(product);
        return Result.success(result);
    }

    /**
     * Delete product
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteProduct(@PathVariable Long id) {
        boolean result = productService.removeById(id);
        return Result.success(result);
    }
}
