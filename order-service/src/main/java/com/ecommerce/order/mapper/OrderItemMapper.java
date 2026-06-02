package com.ecommerce.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.order.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Order Item Mapper
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    List<OrderItem> findByOrderId(Long orderId);
}
