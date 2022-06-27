package com.skilldistillery.mod.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	List <Order> findByUser_Username(String username);
	
	Order findByIdAndUser_Username(int orderId, String username);
	
	

}
