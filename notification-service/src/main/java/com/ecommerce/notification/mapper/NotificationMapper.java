package com.ecommerce.notification.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecommerce.notification.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

/**
 * Notification Mapper
 */
@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {
    Notification findByUserId(Long userId);
}
