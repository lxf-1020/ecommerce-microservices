package com.ecommerce.notification.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ecommerce.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Notification Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("notification_info")
public class Notification extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String type; // EMAIL, SMS, PUSH
    private String title;
    private String content;
    private Integer status; // 0: pending, 1: sent, 2: failed
    private String recipient;
    private String remarks;
}
