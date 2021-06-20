package com.sbrest.demo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.sbrest.demo.entities.User;
import com.sbrest.demo.exceptions.UserExistException;
import com.sbrest.demo.exceptions.UserNameNotFoundException;
import com.sbrest.demo.exceptions.UserNotFoundException;
import com.sbrest.demo.services.UserService;

@RestController
@Validated
public class UserController {
	
	@Autowired
private	UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser( @Valid  @RequestBody User user,UriComponentsBuilder builder ) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistException e) {
			throw new ResponseStatusException(HttpStatus.FOUND, "User Already Exist");
		}
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
		     throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById (@RequestBody User user , @PathVariable("id")Long id)  {
		
		try {
			return userService.updateUserById(user, id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
//		
	}
	@DeleteMapping("/users/{id}")
	public String deleteUserById(@PathVariable("id")Long id) {
		
		try {
			return userService.deleteUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			
		}
		
	}
	
	@GetMapping("/users/byuserName/{uname}")
	public User getUserByUsername(@PathVariable String uname) throws UserNameNotFoundException {
		
		if (userService.getUserByUsername(uname)!=null) {
		return userService.getUserByUsername(uname);
		}else {
			throw new UserNameNotFoundException("User with username "+uname+" does not exist");
		}
	}
	
	

}
