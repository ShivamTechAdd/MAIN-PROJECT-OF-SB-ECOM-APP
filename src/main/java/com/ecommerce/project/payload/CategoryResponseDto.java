package com.ecommerce.project.payload;

import com.ecommerce.project.model.Category;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryResponseDto {
    private List<CategoryDto> content;

}
