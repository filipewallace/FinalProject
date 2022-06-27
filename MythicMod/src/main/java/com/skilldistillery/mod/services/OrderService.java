package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Order;

public interface OrderService {
	
	public List<Order> index();
	
	public Order findOrderById(int id);
	
	public Order createOrder(Order order, int modId);
	
	public Order updateOrder(Order order, int modId);
	
	public Boolean destroyOrder (int id);
	
//	public Order findCustomerOrder(String username, int orderId);
	

}
