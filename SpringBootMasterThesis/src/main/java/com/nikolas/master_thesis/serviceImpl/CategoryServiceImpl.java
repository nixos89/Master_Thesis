package com.nikolas.master_thesis.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolas.master_thesis.dto.AddCategoryDTO;
import com.nikolas.master_thesis.dto.CategoryDTO;
import com.nikolas.master_thesis.dto.CategoryListDTO;
import com.nikolas.master_thesis.exception.StoreException;
import com.nikolas.master_thesis.mapper.CategoryMapper;
import com.nikolas.master_thesis.model.Book;
import com.nikolas.master_thesis.model.Category;
import com.nikolas.master_thesis.repository.CategoryRepository;
import com.nikolas.master_thesis.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	CategoryRepository categoryRepository;
	CategoryMapper categoryMapper;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}


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
		List<Category> categories = categoryRepository.findAllByOrderByCategoryIdAsc();
		if (categories != null && !categories.isEmpty()) {
			for (Category category : categories) {
				categoryListDTO.getCategoryList().add(categoryMapper.mapCategoryToCategoryDTO(category));
			}
			return categoryListDTO;
		} else {
			throw new StoreException("Exception, no categories found!", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public CategoryDTO addCategory(AddCategoryDTO addCategoryDTO) {
		Category category = new Category();
		category.setName(addCategoryDTO.getName());
		category.setDeleted(addCategoryDTO.getIsDeleted());
		category = categoryRepository.save(category);
		return categoryMapper.mapCategoryToCategoryDTO(category);
	}

	@Override
	public Boolean updateCategory(AddCategoryDTO addCategoryDTO, Long id) {
		Category category = categoryRepository.getOne(id);
		if (category == null)
			throw new StoreException("Category doesn't exist!", HttpStatus.NOT_FOUND);

		category.setName(addCategoryDTO.getName());
		category.setDeleted(addCategoryDTO.getIsDeleted());
		categoryRepository.save(category);
		return true;
	}

	@Override
	public Boolean deleteCategory(Long id) {
		Category category = categoryRepository.getOne(id);
		if (category == null)
			throw new StoreException("Category doesn't exist!", HttpStatus.NOT_FOUND);

		Set<Book> books = category.getBooks();
		if (!books.isEmpty()) {
			throw new StoreException("You need to delete books with this category first.", HttpStatus.BAD_REQUEST);
		}

		category.setDeleted(true);
		return true;
	}
	
	
	
	
}
