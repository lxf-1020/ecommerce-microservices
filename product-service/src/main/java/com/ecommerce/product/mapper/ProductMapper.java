package com.ecommerce.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Product Mapper
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    List<Product> findByCategory(Long categoryId);
    List<Product> searchByName(String keyword);
}
