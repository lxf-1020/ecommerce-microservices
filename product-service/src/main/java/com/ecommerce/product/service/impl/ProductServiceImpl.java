package com.ecommerce.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Product Service Implementation
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return baseMapper.findByCategory(categoryId);
    }

    @Override
    public List<Product> searchByName(String keyword) {
        return baseMapper.searchByName(keyword);
    }

    @Override
    public Product getProductDetail(Long id) {
        return baseMapper.selectById(id);
    }
}
