package com.example.demo.persistence;

import com.example.demo.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    List<Category> getCategoryList();
    Category getCategory(String categoryId);
    void updateCategoryName(String newName, String newDesn, String categoryId);
}
