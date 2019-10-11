package com.nikolas.master_thesis.mapper;

import org.mapstruct.Mapper;

import com.nikolas.master_thesis.dto.CategoryDTO;
import com.nikolas.master_thesis.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {	
	Category mapCategoryDTOToCategory(CategoryDTO categoryDTO);
	
	CategoryDTO mapCategoryToCategoryDTO(Category category);	
}
