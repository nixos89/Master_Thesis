package com.nikolas.master_thesis.service;

import com.nikolas.master_thesis.dto.CategoryDTO;
import com.nikolas.master_thesis.dto.CategoryListDTO;

public interface CategoryService {
	
	CategoryDTO getCategory(Long id);
	
	CategoryListDTO findAllCategories();
}
