package com.danielxavier.dsnewcatalog.controllers;

import com.danielxavier.dsnewcatalog.records.RProductDTO;
import com.danielxavier.dsnewcatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<RProductDTO>> findAll(Pageable pageable) {
        Page<RProductDTO> category = service.findAll(pageable);;
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RProductDTO> findById(@PathVariable Long id) {
        RProductDTO category = service.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<RProductDTO> insert(@RequestBody RProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RProductDTO> update(@PathVariable Long id, @RequestBody RProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
