package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Order;

public interface OrderService {
	
	public List<Order> index();
	
	public Order findOrderById(int id);
	
	public Order createOrder(Order order);
	
	public Order updateOrder(Order order);
	
	public Boolean destroyOrder (int id);
	

}
