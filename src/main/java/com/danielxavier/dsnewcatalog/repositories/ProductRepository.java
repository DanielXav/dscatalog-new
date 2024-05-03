package com.danielxavier.dsnewcatalog.repositories;

import com.danielxavier.dsnewcatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
