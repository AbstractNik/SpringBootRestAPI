package com.sbrest.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbrest.demo.beans.UserDetails;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET,path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello SB Rest World--Get Mapping";
	}
	
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		
		return new UserDetails("Nikhil", "Tyagi", "Delhi");
		
	}

}
