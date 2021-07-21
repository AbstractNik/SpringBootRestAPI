package com.sbrest.demo.controller;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbrest.demo.dto.UserMmDTO;
import com.sbrest.demo.entities.User;
import com.sbrest.demo.exceptions.UserNotFoundException;
import com.sbrest.demo.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
	
	@Autowired
	private	UserService userService;
	
	@Autowired
	private ModelMapper modelmapper;
		
	@GetMapping("/{id}")
	public UserMmDTO getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		
			User user= userService.getUserById(id);
			if(user==null) {
				throw new UserNotFoundException("user not found");
			}
			
			UserMmDTO userDto= modelmapper.map(user, UserMmDTO.class);
			return userDto;
	 
	}
	
	

}
