package com.danielxavier.dsnewcatalog.controllers;

import com.danielxavier.dsnewcatalog.dto.CategoryDTO;
import com.danielxavier.dsnewcatalog.entities.Category;
import com.danielxavier.dsnewcatalog.records.RCategoryDTO;
import com.danielxavier.dsnewcatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<RCategoryDTO>> findAll() {
        List<RCategoryDTO> category = service.findAll();;
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RCategoryDTO> findById(@PathVariable Long id) {
        RCategoryDTO category = service.findById(id);
        return ResponseEntity.ok(category);
    }
}
