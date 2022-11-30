package com.blog.backend.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.backend.payloads.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)  
	//by this line we mean to say that this method 
	// will handle the exception of this type of class 
	public ResponseEntity<Response> resourseNotFoundExceptionHandler (ResourceNotFoundException ex){
		
		//with the help of this argument (ex) we will be able to get the error message that we use to throw 
		// from that exception class
		
		String message = ex.getMessage();  //this is the error message that we get from resource 
		// not found class 
		
		Response response = new Response(message,false);
		
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
		
		
		//when ever in controller we get the exception then this method will get invoked
	}
	
	//we will create one exception handler for java bean validate exception
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> beanValidatorException(MethodArgumentNotValidException ex){
		
		//we have to create a map for storing the field name and error default message that we have set
		//first we take out the field name
		
		Map<String,String> resp = new HashMap<>();
		
		//Binding result holds the result of validation and binding
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
		
	}
	
}
