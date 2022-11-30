package com.blog.backend.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

	//this class is used to transfer data from user because we 
	// would not use user class direct in services instead of that we use this
	
	//main moto is that we would not expose our entity directly
	//we would give the api information which is needed
	
	private Integer id;
	
	@NotEmpty(message = "You must have to write your name")
	@Size(min=4,message = "username must be of 4 character")
	private String name;
	
	@Email(message="Email address is not valid")
	private String email;
	
	@NotEmpty(message = "Password is wrong")
	@Size(min = 3 , max = 10 , message = "Password must be min of 3 chars and max of 10.")
	private String password;
	
	@NotEmpty(message = "write something about you!")
	private String about;
}
