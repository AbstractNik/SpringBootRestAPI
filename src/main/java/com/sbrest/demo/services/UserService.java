package com.sbrest.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sbrest.demo.dao.UserRepository;
import com.sbrest.demo.entities.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<User> getAllUsers(){
	List<User>  repoUserList =userRepo.findAll();
		return repoUserList;
		
	}
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	public User getUserById(Long id) {
		Optional<User> user= userRepo.findById(id);
		return user.get();
	}
	
	public User updateUserById (User user , Long id) {
//		Optional<User> userToUpdate= userRepo.findById(id);
//		return userToUpdate.get().s;
		user.setId(id);
		return userRepo.save(user);
	}
	
    public String deleteUserById(Long id) {
    	
    	if (userRepo.findById(id).isPresent()) {
    	userRepo.deleteById(id);
    	return "User with "+id+" deleted";
    } 
    	else {
    	return "User Does not Exist";
    }
}
    
    public User getUserByUsername(String userName) {
    	return userRepo.findByUserName(userName);
    }
}
