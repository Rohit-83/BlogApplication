package com.blog.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.backend.models.User;

public interface UserRepo  extends JpaRepository<User,Integer>{

}
