package com.blog.backend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.backend.payloads.CategoryDto;
import com.blog.backend.payloads.Response;
import com.blog.backend.services.CategoryServices;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	
	@Autowired
	private CategoryServices categoryService;
	//crud
	
	
	//create
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto cat = this.categoryService.createCategory(categoryDto);
	
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.CREATED);
	}
	
	
	
	//update
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updatedCat = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCat,HttpStatus.OK);
	}
	
	
	
	//delete
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<Response> deleteCategory(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<Response>(new Response("Category Deleted with id "+categoryId,true),HttpStatus.OK);
	}
	
	
	
	//get
	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		
		CategoryDto cat = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);
	}
	
	
	@GetMapping("/allCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategoryDto(){
		List<CategoryDto> catL = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(catL,HttpStatus.OK);
	}

}
