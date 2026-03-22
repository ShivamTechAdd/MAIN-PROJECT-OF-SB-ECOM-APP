package com.ecommerce.project.service;

import com.ecommerce.project.payload.CategoryDto;
import com.ecommerce.project.payload.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto getAllCategory();

    CategoryDto createCategory(CategoryDto categoryDto);

    String deleteById(Long categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
}
