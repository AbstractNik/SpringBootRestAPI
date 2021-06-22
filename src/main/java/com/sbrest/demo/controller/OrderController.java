package com.sbrest.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbrest.demo.dao.OrderRepository;
import com.sbrest.demo.dao.UserRepository;
import com.sbrest.demo.entities.Orders;
import com.sbrest.demo.entities.User;
import com.sbrest.demo.exceptions.OrderDoesNotExistException;

import com.sbrest.demo.exceptions.UserExistException;
import com.sbrest.demo.exceptions.UserNotFoundException;

@RestController
@RequestMapping(value="/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userId}/orders")
	public List<Orders> getAllOrders(@PathVariable Long userId) throws UserNotFoundException{
		
		Optional<User> userCheck = userRepository.findById(userId);
		
		if(userCheck.isPresent()) {
			return userCheck.get().getOrders();
		}else {
			throw new UserNotFoundException(" User is not present in the repository ");
		}
		
		
		
		
	}
       @PostMapping("/{userId}/order")
	public Orders createOrder(@RequestBody Orders order, @PathVariable Long userId) throws UserNotFoundException{
		
    	if(userRepository.findById(userId).isPresent())   {
//		if (!(orderRepository.findById(order.getOrderId()).isPresent() && orderRepository.findById(order.getOrderId()).get().getUser().getId()==userId)) {
		order.setUser(userRepository.findById(userId).get());
		return orderRepository.save(order);
//		}else {
//			throw new OrderExistException("Order with id "+order.getOrderId()+" Already Exist ");
//		}
    	}else {
    		throw new UserNotFoundException("User with id "+userId+" does not  exisit ");
    		
    	}
		
		
		
		
	}
       @GetMapping("/{userId}/order/{orderId}")
       public Orders getOrderByOrderId(@PathVariable Long userId,@PathVariable Long orderId) throws UserNotFoundException,OrderDoesNotExistException {
    	   if(userRepository.findById(userId).isPresent())   {
    		   if(orderRepository.findById(orderId).isPresent()) {
    			   
    			return   orderRepository.findById(orderId).get();
    			   
    		   }else {
    			   throw new OrderDoesNotExistException("Order with id "+orderId+" does not  exisit ");
    		   }
    		   
    	   }else {
       		throw new UserNotFoundException("User with id "+userId+" does not  exisit ");
    		
       	}
		
    	   
       }
}
