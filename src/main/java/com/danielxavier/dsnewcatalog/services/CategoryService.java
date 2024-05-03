package com.danielxavier.dsnewcatalog.services;

import com.danielxavier.dsnewcatalog.dto.CategoryDTO;
import com.danielxavier.dsnewcatalog.entities.Category;
import com.danielxavier.dsnewcatalog.records.RCategoryDTO;
import com.danielxavier.dsnewcatalog.repositories.CategoryRepository;
import com.danielxavier.dsnewcatalog.services.exceptions.ResouceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public RCategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);

        return RCategoryDTO.fromCategory(obj.orElseThrow(() -> new ResouceNotFoundException("Entity not found")));
    }

    /*@Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category = repository.save(category);
        return new CategoryDTO(category);
    }*/

    @Transactional
    public RCategoryDTO insert(RCategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.name());
        category = repository.save(category);
        return RCategoryDTO.fromCategory(category);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        try {
            Category category = repository.getReferenceById(id);
            category.setName(categoryDTO.getName());
            category = repository.save(category);
            return new CategoryDTO(category);
        }
        catch (EntityNotFoundException e) {
            throw new ResouceNotFoundException("Id not found " + id);
        }
    }
}
