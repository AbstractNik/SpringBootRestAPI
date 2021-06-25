package com.sbrest.demo.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sbrest.demo.entities.User;
import com.sbrest.demo.exceptions.UserNotFoundException;
import com.sbrest.demo.services.UserService;

@RestController
@RequestMapping(value="/jacksonfilters/users")
@Validated
public class UserMappingJacksonController {
	
	@Autowired
	private	UserService userService;
	
	
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			User user=userService.getUserById(id);
			Set<String> properties= new HashSet<String>();
			properties.add("id");
			properties.add("firstName");
			properties.add("lastName");
			properties.add("orders");
			FilterProvider filterProvider= new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(properties));
			MappingJacksonValue mapper= new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException e) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping("/params/{id}")
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id,
			@RequestParam Set<String> properties) {
		try {
			User user=userService.getUserById(id);
//			Set<String> properties= new HashSet<String>();
//			properties.add("id");
//			properties.add("firstName");
//			properties.add("lastName");
//			properties.add("orders");
			FilterProvider filterProvider= new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(properties));
			MappingJacksonValue mapper= new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException e) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
