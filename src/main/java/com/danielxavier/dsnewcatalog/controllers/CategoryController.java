package com.danielxavier.dsnewcatalog.controllers;

import com.danielxavier.dsnewcatalog.dto.CategoryDTO;
import com.danielxavier.dsnewcatalog.entities.Category;
import com.danielxavier.dsnewcatalog.records.RCategoryDTO;
import com.danielxavier.dsnewcatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<RCategoryDTO> insert(@RequestBody RCategoryDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RCategoryDTO> update(@PathVariable Long id, @RequestBody RCategoryDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
