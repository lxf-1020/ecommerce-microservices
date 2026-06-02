package com.ecommerce.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ecommerce.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * Product Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("product_info")
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String productName;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Long categoryId;
    private String imageUrl;
    private Integer status; // 0: active, 1: inactive
}
