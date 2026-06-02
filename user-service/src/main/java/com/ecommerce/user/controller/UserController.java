package com.ecommerce.user.controller;

import com.ecommerce.common.dto.Result;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get user by ID
     */
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    /**
     * Get user by username
     */
    @GetMapping("/username/{username}")
    public Result<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    /**
     * User registration
     */
    @PostMapping("/register")
    public Result<User> register(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam String email,
                                  @RequestParam String phone) {
        User user = userService.register(username, password, email, phone);
        return Result.success(user);
    }

    /**
     * User login
     */
    @PostMapping("/login")
    public Result<Boolean> login(@RequestParam String username,
                                  @RequestParam String password) {
        boolean result = userService.login(username, password);
        return Result.success(result);
    }

    /**
     * Update user info
     */
    @PutMapping("/{id}")
    public Result<Boolean> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        boolean result = userService.updateById(user);
        return Result.success(result);
    }
}
