package com.sbrest.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbrest.demo.beans.UserDetails;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	//@RequestMapping(method=RequestMethod.GET,path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello SB Rest World--Get Mapping";
	}
	
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		
		return new UserDetails("Nikhil", "Tyagi", "Delhi");
		
	}
	@GetMapping("/hello-int")
	public String getMessageI18n(@RequestHeader(name="Accept-Language",required=false) String locale) {
		return messageSource.getMessage("label.hello",null, new Locale(locale));
	}
	@GetMapping("/hello-int2")
	public String getMessageI18n1() {
		return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
	}

}
