package com.danielxavier.dsnewcatalog.repositories;

import com.danielxavier.dsnewcatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
