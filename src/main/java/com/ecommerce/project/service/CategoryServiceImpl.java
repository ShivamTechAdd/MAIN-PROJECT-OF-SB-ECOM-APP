package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.ApiException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDto;
import com.ecommerce.project.payload.CategoryResponseDto;
import com.ecommerce.project.repository.CategoryRepoitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepoitory categoryRepoitory;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponseDto getAllCategory() {
        List<Category> categories = categoryRepoitory.findAll();
        if(categories.isEmpty())
            throw new ApiException("No Category Created Till Now.");
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setContent(categoryDtos);
        return categoryResponseDto;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) { //this category is validated to Unique.
        Category category = modelMapper.map(categoryDto,Category.class);
        Category savedCategory = categoryRepoitory.findByCategoryName(category.getCategoryName());
        if(savedCategory != null) //already exist in database
            throw new ApiException("Category with the name ("+category.getCategoryName()+") already exists !!!!!.");
        Category savedCategories = categoryRepoitory.save(category);
        CategoryDto savedCategoryDto = modelMapper.map(savedCategories,CategoryDto.class);
        return savedCategoryDto;
    }

    @Override
    public String deleteById(Long categoryId) {
        Category category= categoryRepoitory.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));

        categoryRepoitory.delete(category);
        return "Category With "+categoryId+" is deleted";
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category savedCategory = categoryRepoitory.findById(categoryId)
                .orElseThrow();

        Category category = modelMapper.map(categoryDto,Category.class);
        category.setCategoryId(categoryId);
        category = categoryRepoitory.save(category);
        return modelMapper.map(savedCategory,CategoryDto.class);
    }


}













//Optional<Category> optcategory = categories.stream()
//        .filter(c->c.getCategoryId().equals(categoryId))
//        .findFirst();

//        if(optcategory.isPresent()){
//Category existingCategory = optcategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//Category savedCategory = categoryRepoitory.save(existingCategory);
//            return savedCategory;
//        }
//                else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not found to get Updated");













