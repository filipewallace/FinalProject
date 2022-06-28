package com.skilldistillery.mod.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.Mod;
import com.skilldistillery.mod.entities.Order;
import com.skilldistillery.mod.repositories.ModRepository;
import com.skilldistillery.mod.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository ordRepo;
	
	@Autowired
	ModRepository modRepo;

	@Override
	public List<Order> index() {

		return ordRepo.findAll();
	}

	@Override
	public Order findOrderById(int id) {
		Optional<Order> optOrder = ordRepo.findById(id);

		if (optOrder.isPresent()) {
			Order order = optOrder.get();
			return order;
		}
		return null;
	}

	@Override
	public Order createOrder(Order order, int modId) {
		
		Mod mod=modRepo.queryById(modId);
//		order.setMods(new ArrayList<>());
//		order.getMods().add(mod);
		order.addMod(mod);
		return ordRepo.saveAndFlush(order);
	}

	@Override
	public Order updateOrder(Order order, int modId) {
		
		Mod mod=modRepo.queryById(modId);
		order.addMod(mod);
		return ordRepo.saveAndFlush(order);
	}

	@Override
	public Boolean destroyOrder(int id) {
		ordRepo.deleteById(id);
		boolean deleted = !ordRepo.existsById(id);
		return deleted;
	}

//	public Order findCustomerOrder(String username, int orderId) {
//
//		Order order = ordRepo.findByOrder_IdAndUser_Username(orderId, username);
//
//		return order;
//
//	}

}
