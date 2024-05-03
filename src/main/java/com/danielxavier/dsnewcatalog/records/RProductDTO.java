package com.danielxavier.dsnewcatalog.records;

import com.danielxavier.dsnewcatalog.entities.Category;
import com.danielxavier.dsnewcatalog.entities.Product;

import java.time.Instant;
import java.util.List;

public record RProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant Date, List<RCategoryDTO> categories) {
    public static RProductDTO fromProduct(Product product) {
        return new RProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl(),
                product.getDate(),
                List.of()
        );
    }

    public static RProductDTO fromProductWithCategories(Product product, List<Category> categories) {
        List<RCategoryDTO> categoryDTOs = categories.stream().map(RCategoryDTO::fromCategory).toList();

        return new RProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl(),
                product.getDate(),
                categoryDTOs
        );
    }
}
