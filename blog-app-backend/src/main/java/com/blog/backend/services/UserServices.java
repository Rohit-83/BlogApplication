package com.blog.backend.services;

import java.util.List;

import com.blog.backend.payloads.UserDto;

public interface UserServices {
	
	UserDto createUser(UserDto userDto);
	
	UserDto getUserbyId(Integer userDtoId);
	
	UserDto updateUser(UserDto userDto,Integer userDtoId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userDtoId);

}
