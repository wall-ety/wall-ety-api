package com.hei.wallet.wallety.endpoint.rest.controller;

import com.hei.wallet.wallety.model.Category;
import com.hei.wallet.wallety.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAll(){
        return categoryService.findAll();
    }

    @GetMapping("/categories/{categoryId}")
    public Category getById(@PathVariable String categoryId){
        return categoryService.findById(categoryId);
    }

    @PutMapping("/categories")
    public List<Category> saveOrUpdateAll(@RequestBody List<Category> categories){
        return categoryService.saveOrUpdateAll(categories);
    }
}
