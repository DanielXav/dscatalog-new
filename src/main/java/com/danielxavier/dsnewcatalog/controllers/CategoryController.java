package com.danielxavier.dsnewcatalog.controllers;

import com.danielxavier.dsnewcatalog.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    public ResponseEntity<Category> findAll() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Jubileu");
        return ResponseEntity.ok(category);
    }
}
