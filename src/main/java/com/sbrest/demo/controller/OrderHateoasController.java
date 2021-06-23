package com.sbrest.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbrest.demo.dao.OrderRepository;
import com.sbrest.demo.dao.UserRepository;
import com.sbrest.demo.entities.Orders;
import com.sbrest.demo.entities.User;
import com.sbrest.demo.exceptions.UserNotFoundException;

@RestController
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@GetMapping("/{userId}/orders")
	public List<Orders> getAllOrders(@PathVariable Long userId) throws UserNotFoundException{
		
		Optional<User> userCheck = userRepository.findById(userId);
		
		if(userCheck.isPresent()) {
			List<Orders> ordersList= userCheck.get().getOrders();
//			for (int userOrder=0;userOrder<ordersList.size();userOrder++) {
//				Orders order = ordersList.get(userOrder);
//				Long idForOrder = order.getOrderId();
//				Link selfLink= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId)(idForOrder)).withSelfRel();	
//			}
			
			return ordersList;
		}else {
			throw new UserNotFoundException(" User is not present in the repository ");
		}
		
		
		
		
	}

}
