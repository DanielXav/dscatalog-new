package com.danielxavier.dsnewcatalog.services;

import com.danielxavier.dsnewcatalog.entities.Category;
import com.danielxavier.dsnewcatalog.entities.Product;
import com.danielxavier.dsnewcatalog.records.RCategoryDTO;
import com.danielxavier.dsnewcatalog.records.RProductDTO;
import com.danielxavier.dsnewcatalog.repositories.CategoryRepository;
import com.danielxavier.dsnewcatalog.repositories.ProductRepository;
import com.danielxavier.dsnewcatalog.services.exceptions.DatabaseException;
import com.danielxavier.dsnewcatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Page<RProductDTO> findAll(Pageable pageable) {
        Page<Product> entities = repository.findAll(pageable);

        return entities.map(RProductDTO::fromProduct);
    }

    @Transactional
    public RProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return RProductDTO.fromProductWithCategories(product, new ArrayList<>(product.getCategories()));
    }

    @Transactional
    public RProductDTO insert(RProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product, "id", "categories");
        product.setCategories(convertCategoryDTOsToEntities(dto.categories()));
        product = repository.save(product);
        return RProductDTO.fromProduct(product);
    }

    @Transactional
    public RProductDTO update(Long id, RProductDTO dto) {
        try {
            Product product = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, product, "id", "categories");
            product.setCategories(convertCategoryDTOsToEntities(dto.categories()));
            product = repository.save(product);
            return RProductDTO.fromProduct(product);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private Set<Category> convertCategoryDTOsToEntities(List<RCategoryDTO> categoryDTOs) {
        Set<Category> categories = new HashSet<>();
        for (RCategoryDTO catDto : categoryDTOs) {
            Category category = categoryRepository.findById(catDto.id()).orElseThrow(
                    () -> new ResourceNotFoundException("Category not found with id " + catDto.id())
            );
            categories.add(category);
        }
        return categories;
    }
}
