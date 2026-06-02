package com.ecommerce.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.product.entity.Product;
import java.util.List;

/**
 * Product Service Interface
 */
public interface ProductService extends IService<Product> {
    List<Product> findByCategory(Long categoryId);
    List<Product> searchByName(String keyword);
    Product getProductDetail(Long id);
}
