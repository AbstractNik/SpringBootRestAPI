package com.sbrest.demo.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbrest.demo.dao.UserRepository;
import com.sbrest.demo.entities.User;
import com.sbrest.demo.exceptions.UserNotFoundException;
import com.sbrest.demo.services.UserService;

@RestController
@Validated//this is used to validate the size of path variable like id as we have already seen 
@RequestMapping(value="/hateoas/users")

public class UserHateoasController {
	
	@Autowired
private	UserService userService;

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			User user=userService.getUserById(id);
			Long userId = user.getId();
			Link selfLink= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserHateoasController.class).getUserById(userId)).withSelfRel();	
			user.add(selfLink);
			return user;
					} catch (UserNotFoundException e) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping
	public List<User> getAllUsers() throws UserNotFoundException{
		
		List<User> userList= userService.getAllUsers();
		List<User> manipulateduserList= new ArrayList<User>();
		if(userList.size()>0) {
		for (int userEntry=0;userEntry<userList.size();userEntry++) {
			User user= userList.get(userEntry);
			Long userId = user.getId();
			Link selfLink= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserHateoasController.class).getUserById(userId)).withSelfRel();	
			user.add(selfLink);
			Link orderLink= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId)).withRel("all-orders");	
			user.add(orderLink);
	Link selfLinkForAllUsers= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserHateoasController.class).getAllUsers()).withRel("all-users");
		user.add(selfLinkForAllUsers);
			manipulateduserList.add(user);
		}
		}
		
		//manipulateduserList.
		return manipulateduserList;
	}

}
