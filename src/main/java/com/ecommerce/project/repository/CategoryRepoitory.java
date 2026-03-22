package com.ecommerce.project.repository;

import com.ecommerce.project.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepoitory extends JpaRepository<Category,Long> {

    Category findByCategoryName(String categoryName); // No need to Impl or override It Jpa Automaticaticaly analyse the declaration over here understand what do you want and automatically generate the implementation on the fly for the same;


}
