package com.sbrest.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sbrest.demo.dao.UserRepository;
import com.sbrest.demo.entities.User;
import com.sbrest.demo.exceptions.UserExistException;
import com.sbrest.demo.exceptions.UserNotFoundException;

@Service
public class UserService  {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<User> getAllUsers(){
	List<User>  repoUserList =userRepo.findAll();
		return repoUserList;
		
	}
	
	public User createUser(User user) throws UserExistException {
		
		if (getUserByUsername(user.getUserName())!=null) {
			throw new UserExistException(" User already exist exception ");
		}
		return userRepo.save(user);
	}
	
	public User getUserById  (Long id) throws UserNotFoundException {
		Optional<User> user= userRepo.findById(id);
		if(user.isPresent()) {
		return user.get();
	}else {
		throw new UserNotFoundException(" User is not present in the repository ");
	}
	}
	
	public User updateUserById (User user , Long id) throws UserNotFoundException{
      Optional<User> userToUpdate= userRepo.findById(id);
      
//		return userToUpdate.get().s;
      
      if(userToUpdate.isPresent()) {
		user.setId(id);
		return userRepo.save(user);
	}
      else {
    	  throw new UserNotFoundException("User is not present in the repository");
      }
	}
	
    public String deleteUserById(Long id) throws UserNotFoundException {
    	
    	if (userRepo.findById(id).isPresent()) {
    	userRepo.deleteById(id);
    	return "User with "+id+" deleted";
    } 
    	else {
    	throw new UserNotFoundException("User is not present for deletion");
    }
}
    
    public User getUserByUsername(String userName) {
    	return userRepo.findByUserName(userName);
    }
}
