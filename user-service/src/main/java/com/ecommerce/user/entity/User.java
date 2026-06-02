package com.ecommerce.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ecommerce.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String email;
    private String phone;
    private String realName;
    private Integer status; // 0: normal, 1: disabled
}
