package com.blog.backend.services;

import java.util.List;

import com.blog.backend.payloads.CategoryDto;

public interface CategoryServices {
	
	//Crud
	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	
	//get single category
	public CategoryDto getCategory(Integer categoryId);
	
	//get AllCategory
	public List<CategoryDto> getAllCategory();
}
