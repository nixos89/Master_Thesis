package com.nikolas.master_thesis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolas.master_thesis.dto.CategoryDTO;
import com.nikolas.master_thesis.dto.CategoryListDTO;
import com.nikolas.master_thesis.exception.StoreException;
import com.nikolas.master_thesis.mapper.CategoryMapper;
import com.nikolas.master_thesis.model.Category;
import com.nikolas.master_thesis.repository.CategoryRepository;
import com.nikolas.master_thesis.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryMapper categoryMapper;
	
	@Override
	public CategoryDTO getCategory(Long id) {
		Category category = categoryRepository.getOne(id);
		if (category != null) {
			return categoryMapper.mapCategoryToCategoryDTO(category);
		} else {
			throw new StoreException("No category found!", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public CategoryListDTO findAllCategories() {
		CategoryListDTO categoryListDTO = new CategoryListDTO();
		List<Category> categories = categoryRepository.findAll();
		if (categories != null && !categories.isEmpty()) {
			for (Category category : categories) {
				categoryListDTO.getCategoryList().add(categoryMapper.mapCategoryToCategoryDTO(category));
			}
			return categoryListDTO;
		} else {
			throw new StoreException("Exception, no categories found!", HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}
