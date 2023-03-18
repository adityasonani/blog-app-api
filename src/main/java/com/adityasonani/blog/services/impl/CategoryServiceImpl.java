package com.adityasonani.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adityasonani.blog.entities.Category;
import com.adityasonani.blog.exceptions.ResourceNotFoundException;
import com.adityasonani.blog.payloads.CategoryDto;
import com.adityasonani.blog.repositories.CategoryRepo;
import com.adityasonani.blog.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto category) {
		Category savedCat = categoryRepo.save(dtoToCat(category));
		return catToDto(savedCat);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());

		Category updatedCategory = categoryRepo.save(category);
		return catToDto(updatedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		return catToDto(category);
	}

	@Override
	public void deleteCategoryById(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		categoryRepo.delete(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<CategoryDto> allCats = categoryRepo.findAll().stream().map((cat) -> catToDto(cat))
				.collect(Collectors.toList());
		return allCats;
	}

	private Category dtoToCat(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}

	private CategoryDto catToDto(Category category) {
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
