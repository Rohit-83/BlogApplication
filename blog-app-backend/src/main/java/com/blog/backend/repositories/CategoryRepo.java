package com.blog.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.backend.models.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
