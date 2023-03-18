package com.adityasonani.blog.services;

import java.util.List;

import com.adityasonani.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto category);

	CategoryDto updateCategory(CategoryDto category, Integer categoryId);

	CategoryDto getCategoryById(Integer categoryId);

	void deleteCategoryById(Integer categoryId);

	List<CategoryDto> getAllCategories();
	
}
