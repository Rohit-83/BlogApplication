package com.blog.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.backend.exceptions.ResourceNotFoundException;
import com.blog.backend.models.User;
import com.blog.backend.payloads.UserDto;
import com.blog.backend.repositories.UserRepo;
import com.blog.backend.services.UserServices;


@Service
public class UserServiceImpl implements UserServices {
	
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		//we need user here and we have userDto so first we convert
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		//as we have to return userDto so now we have to convert user into dto
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto getUserbyId(Integer userDtoId) {
		
		User user = this.userRepo.findById(userDtoId).orElseThrow(()-> 
		new ResourceNotFoundException("User","ID",userDtoId));
		
		// first two parameter that is user and id are string value that will we reflected as written 
		// here and third parameter will only change
		
		return this.userToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userDtoId) {
		
		//first we have to fetch the user which we have to update by id
		
		User user = this.userRepo.findById(userDtoId).orElseThrow(()-> 
		new ResourceNotFoundException("User","ID",userDtoId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		
		 //this is important we have to save in repo what we have updated
		User saveUser = this.userRepo.save(user);
		
		//now we have to change user into userDto
		UserDto newUser = this.userToDto(saveUser);
		
		return newUser;
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		
		List<User> user = this.userRepo.findAll();
		//we have user as a list here so we have to typecast this
		
		List<UserDto> newUser = user.stream().map(users->this.userToDto(users)).collect(Collectors.toList());
		
		return newUser;
	}

	@Override
	public void deleteUser(Integer userDtoId) {
		
		//first we get user by id 
		User user = this.userRepo.findById(userDtoId).orElseThrow(()-> 
		new ResourceNotFoundException("User","ID",userDtoId));
		
		this.userRepo.delete(user);

	}
	
	
	//here we have to make a method which can change userDto into user because UserRepo 
	// takes user as a parameter
	
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
		
	//	user.setId(userDto.getId());
	//	user.setName(userDto.getName());
	//	user.setEmail(userDto.getEmail());
	//	user.setPassword(userDto.getPassword());
	//	user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	//here we make a method for converting user into userDto
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		// userDto.setId(user.getId());
		// userDto.setName(user.getName());
		// userDto.setEmail(user.getEmail());
		// userDto.setPassword(user.getPassword());
		// userDto.setAbout(user.getAbout());
		
		return userDto;
	}
}
