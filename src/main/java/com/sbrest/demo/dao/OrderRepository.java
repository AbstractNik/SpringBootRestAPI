package com.sbrest.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbrest.demo.entities.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
	
	

}
