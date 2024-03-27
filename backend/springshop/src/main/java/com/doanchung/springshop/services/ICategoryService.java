package com.doanchung.springshop.services;

import com.doanchung.springshop.dtos.CategoryDTO;
import com.doanchung.springshop.models.Category;

import java.util.List;

public interface ICategoryService {

    Category createCategory(CategoryDTO category);
    Category getCategoryById(long id);
    List<Category> getAllCategories();
    Category updateCategory(long categoryId, CategoryDTO category);
    void deleteCategory(long id);


}
