package com.ecommerce.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecommerce.user.entity.User;

/**
 * User Service Interface
 */
public interface UserService extends IService<User> {
    User findByUsername(String username);
    User register(String username, String password, String email, String phone);
    boolean login(String username, String password);
}
