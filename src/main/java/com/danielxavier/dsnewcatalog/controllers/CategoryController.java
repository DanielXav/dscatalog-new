package com.danielxavier.dsnewcatalog.controllers;

import com.danielxavier.dsnewcatalog.entities.Category;
import com.danielxavier.dsnewcatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> category;
        category = service.findAll();
        return ResponseEntity.ok(category);
    }
}
