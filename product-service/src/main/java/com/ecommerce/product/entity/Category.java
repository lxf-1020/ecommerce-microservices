package com.ecommerce.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ecommerce.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product Category Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("product_category")
public class Category extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String categoryName;
    private String description;
    private Long parentId;
}
