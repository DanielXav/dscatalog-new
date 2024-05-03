package com.danielxavier.dsnewcatalog.services;

import com.danielxavier.dsnewcatalog.entities.Product;
import com.danielxavier.dsnewcatalog.records.RProductDTO;
import com.danielxavier.dsnewcatalog.repositories.ProductRepository;
import com.danielxavier.dsnewcatalog.services.exceptions.DatabaseException;
import com.danielxavier.dsnewcatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

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
        Product category = new Product();
        //category.setName(dto.name());
        category = repository.save(category);
        return RProductDTO.fromProduct(category);
    }

    @Transactional
    public RProductDTO update(Long id, RProductDTO dto) {
        try {
            Product category = repository.getReferenceById(id);
            //category.setName(dto.name());
            category = repository.save(category);
            return RProductDTO.fromProduct(category);
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
}
