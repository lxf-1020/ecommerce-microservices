package com.ecommerce.product.controller;

import com.ecommerce.common.dto.Result;
import com.ecommerce.product.entity.Category;
import com.ecommerce.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Category Controller
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Get all categories
     */
    @GetMapping
    public Result<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }

    /**
     * Get category by ID
     */
    @GetMapping("/{id}")
    public Result<Category> getCategory(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    /**
     * Create category
     */
    @PostMapping
    public Result<Category> createCategory(@RequestBody Category category) {
        categoryService.save(category);
        return Result.success(category);
    }

    /**
     * Update category
     */
    @PutMapping("/{id}")
    public Result<Boolean> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        boolean result = categoryService.updateById(category);
        return Result.success(result);
    }
}
