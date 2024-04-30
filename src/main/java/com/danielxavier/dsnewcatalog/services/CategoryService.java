package com.danielxavier.dsnewcatalog.services;

import com.danielxavier.dsnewcatalog.dto.CategoryDTO;
import com.danielxavier.dsnewcatalog.entities.Category;
import com.danielxavier.dsnewcatalog.records.RCategoryDTO;
import com.danielxavier.dsnewcatalog.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CancellationException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    /*@Transactional
    public List<CategoryDTO> findAll() {
        List<Category> entity = repository.findAll();

        List<CategoryDTO> categories = entity.stream().map(CategoryDTO::new).toList();;

        return categories;
    }*/

    @Transactional
    public List<RCategoryDTO> findAll() {
        List<Category> entities = repository.findAll();

        return entities.stream()
                .map(RCategoryDTO::fromCategory)
                .toList();
    }

    /*@Transactional
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category category = obj.get();
        return new CategoryDTO(category);
    }*/

    public RCategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return RCategoryDTO.fromCategory(obj.get());
    }
}
