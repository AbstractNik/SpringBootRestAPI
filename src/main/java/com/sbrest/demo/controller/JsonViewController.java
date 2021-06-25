package com.sbrest.demo.controller;

//import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.sbrest.demo.entities.User;
import com.sbrest.demo.entities.Views;
import com.sbrest.demo.exceptions.UserNotFoundException;
import com.sbrest.demo.services.UserService;

@RestController
@Validated
@RequestMapping( value="/jsonView/users")
public class JsonViewController {
	
	@Autowired
private	UserService userService;
	
	@GetMapping("/external/{id}")
	@JsonView(Views.External.class)
	public User getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping("/internal/{id}")
	@JsonView(Views.Internal.class)
	public User getUserById1(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
