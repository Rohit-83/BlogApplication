package com.blog.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.backend.exceptions.ResourceNotFoundException;
import com.blog.backend.models.Category;
import com.blog.backend.payloads.CategoryDto;
import com.blog.backend.repositories.CategoryRepo;
import com.blog.backend.services.CategoryServices;

@Service
public class CategoryServicesImp implements CategoryServices {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	//we use model mapper for converting from dto
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// first we have to change category dto into category
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category newCat = categoryRepo.save(cat);
		
		//as we have to return the dto so we have to again change main class into dto
		
		CategoryDto finalcat = this.modelMapper.map(newCat, CategoryDto.class);
		
		return finalcat;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		//first we have to get the category from id
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(
				"Category","CategoryId",categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		
		Category updatedCat = this.categoryRepo.save(cat);
		CategoryDto newCat = this.modelMapper.map(updatedCat, CategoryDto.class);
		
		return newCat;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// here also first we have to get category by id if not found throw exception
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(
				"Category","CategoryId",categoryId));
		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException(
				"Category","CategoryId",categoryId));
		
		//now we have to convert into dto
		CategoryDto newCat = this.modelMapper.map(cat, CategoryDto.class);
		
		
		return newCat;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> allCat = this.categoryRepo.findAll();
		List<CategoryDto> newCate = allCat.stream().map( category -> this.modelMapper.map(category, CategoryDto.class)).collect(
				Collectors.toList());
		return newCate;
	}

}
