package com.sbrest.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class CustomRestControllerAdviseEH {
	
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	protected CustomErrorDetails handleUserNameNotFoundException(UserNameNotFoundException ex) {
		
		CustomErrorDetails errorDetails= new CustomErrorDetails(new Date(),
				"User Name not found  from GEH-RestControllerAdvice", ex.getMessage());
		
		return errorDetails;
		
}

}
