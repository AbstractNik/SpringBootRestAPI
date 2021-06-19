package com.sbrest.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbrest.demo.entities.User;
import com.sbrest.demo.services.UserService;

@RestController
public class UserController {
	
	@Autowired
private	UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById (@RequestBody User user , @PathVariable("id")Long id) {
		
		return userService.updateUserById(user, id);
//		
	}
	@DeleteMapping("/users/{id}")
	public String deleteUserById(@PathVariable("id")Long id) {
		
		return userService.deleteUserById(id);
		
	}
	
	@GetMapping("/users/byuserName/{uname}")
	public User getUserByUsername(@PathVariable String uname) {
		return userService.getUserByUsername(uname);
	}
	
	

}
