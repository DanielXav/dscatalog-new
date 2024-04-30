package com.danielxavier.dsnewcatalog.services;

import com.danielxavier.dsnewcatalog.entities.Category;
import com.danielxavier.dsnewcatalog.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional
    public List<Category> findAll() {
        return repository.findAll();
    }
}
