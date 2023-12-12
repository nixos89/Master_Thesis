package com.nikolas.master_thesis.service;

import com.nikolas.master_thesis.dto.AddCategoryDTO;
import com.nikolas.master_thesis.dto.CategoryDTO;
import com.nikolas.master_thesis.dto.CategoryListDTO;

public interface CategoryService {

	CategoryDTO getCategory(Long id);
	CategoryListDTO findAllCategories();
	CategoryDTO addCategory(AddCategoryDTO addCategoryDTO);
	Boolean updateCategory(AddCategoryDTO addCategoryDTO, Long id);
	Boolean deleteCategory(Long id);

}
