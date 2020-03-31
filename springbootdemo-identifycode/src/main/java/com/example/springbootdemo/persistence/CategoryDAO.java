package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Catagory;

import java.util.List;

public interface CategoryDAO {
    List<Catagory> getCategoryList();

    Catagory getCategory(String categoryId);
}
