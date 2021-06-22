package com.sbrest.demo.exceptions;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionalHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		//return handleExceptionInternal(ex, null, headers, status, request);
		
		CustomErrorDetails errorDetails= new CustomErrorDetails(new Date(),
				"Methods Argument Invalid from GEH", ex.getMessage());
		
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

//		pageNotFoundLogger.warn(ex.getMessage());
//
//		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
//		if (!CollectionUtils.isEmpty(supportedMethods)) {
//			headers.setAllow(supportedMethods);
//		}
//		return handleExceptionInternal(ex, null, headers, status, request);
		CustomErrorDetails errorDetails= new CustomErrorDetails(new Date(),
				"Http method not supported from GEH", ex.getMessage());
		
		return new ResponseEntity<Object>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	protected ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex) {
		
		CustomErrorDetails errorDetails= new CustomErrorDetails(new Date(),
				"User Name not found  from GEH", ex.getMessage());
		
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
		
}
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		
		CustomErrorDetails errorDetails= new CustomErrorDetails(new Date(),
				"Minimum length of ID field not matched ", ex.getMessage());
		
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
