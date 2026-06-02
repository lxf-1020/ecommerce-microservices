package com.ecommerce.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.service.UserService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * User Service Implementation
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public User register(String username, String password, String email, String phone) {
        User existUser = findByUsername(username);
        if (existUser != null) {
            throw new BusinessException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // In production, should be encrypted
        user.setEmail(email);
        user.setPhone(phone);
        user.setStatus(0);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        save(user);
        return user;
    }

    @Override
    public boolean login(String username, String password) {
        User user = findByUsername(username);
        if (user == null) {
            throw new BusinessException("User not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new BusinessException("Password incorrect");
        }
        return true;
    }
}
