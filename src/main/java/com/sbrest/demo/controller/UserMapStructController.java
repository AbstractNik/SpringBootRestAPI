package com.sbrest.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbrest.demo.dao.UserRepository;
import com.sbrest.demo.dto.UserMsDto;
import com.sbrest.demo.mapper.UserMapper;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userrepo;
	
	@GetMapping
	public List<UserMsDto> getAllUserDtos(){
		
		return userMapper.userListToUserMsDTOList(userrepo.findAll());
		
	}

}
