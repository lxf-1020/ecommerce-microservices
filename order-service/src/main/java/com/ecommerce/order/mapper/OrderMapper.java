package com.ecommerce.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Order Mapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> findByUserId(Long userId);
    Order findByOrderNo(String orderNo);
}
