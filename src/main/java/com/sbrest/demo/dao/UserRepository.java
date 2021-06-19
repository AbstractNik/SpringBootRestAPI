package com.sbrest.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbrest.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserName(String userName);
}
