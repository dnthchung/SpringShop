package com.doanchung.springshop.services;

import com.doanchung.springshop.dtos.CategoryDTO;
import com.doanchung.springshop.models.Category;
import com.doanchung.springshop.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepo categoryRepo;

//    public CategoryService(CategoryRepo categoryRepo) {
//        this.categoryRepo = categoryRepo;
//    }

    @Override
    public Category  createCategory(CategoryDTO categoryDTO) {
        Category newCate = Category
                .builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepo.save(newCate);
    }

    @Override
    public Category getCategoryById(long id) {
        //return categoryRepo.findById(id).orElse(null);
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("cate not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category updateCategory(long categoryId, CategoryDTO categoryDTO) {
        //find category with categoryId
        Category cateFound = getCategoryById(categoryId);
        cateFound.setName(categoryDTO.getName());
        return cateFound;
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepo.deleteById(id);
    }
}
