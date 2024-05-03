package com.danielxavier.dsnewcatalog.records;

import com.danielxavier.dsnewcatalog.entities.Category;

public record RCategoryDTO(Long id, String name) {


    public static RCategoryDTO fromCategory(Category category){
        return new RCategoryDTO(category.getId(), category.getName());
    }

}
