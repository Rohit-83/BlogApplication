package com.blog.backend.controllers;

import java.util.List;
import java.util.Map;

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

import com.blog.backend.payloads.Response;
import com.blog.backend.payloads.UserDto;
import com.blog.backend.services.UserServices;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	//for create user post mapping
	//we use @valid for java bean validation
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createdUser = this.userServices.createUser(userDto);
		
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	
	}
	
	
	//to update user we use put mapping
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
		
		UserDto updatedUser =userServices.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	
	//to delete user we use delete mapping
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Response> deleteUser(@PathVariable Integer userId){
		userServices.deleteUser(userId);
		//we would create a response api
		return new ResponseEntity<Response>(new Response("User deleted",true),HttpStatus.OK);
		
	}
	
	
	//to get user we use get mapping
	@GetMapping("/allUser")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(userServices.getAllUsers());
	}
	
	
	//for single user
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
		
		UserDto users = userServices.getUserbyId(userId);
		return ResponseEntity.ok(users);
		
	}

}
