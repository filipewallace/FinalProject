package com.skilldistillery.mod.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mod.entities.Order;
import com.skilldistillery.mod.services.OrderService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class OrderController {

	@Autowired
	private OrderService ordServ;

	@GetMapping("order")
	public List<Order> index() {
		return ordServ.index();
	}

	@GetMapping("order/{id}")
	public Order listOrderById(

			@PathVariable int id, HttpServletResponse res) {

		if (ordServ.findOrderById(id) == null) {
			res.setStatus(404);
		} else {

			return ordServ.findOrderById(id);
		}
		return null;
	}

	@PostMapping("order/{modId}")
	public Order addNewOrder(

			@RequestBody Order order, @PathVariable int modId, HttpServletRequest req, HttpServletResponse res) {

		try {
			order = ordServ.createOrder(order, modId);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(order.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {

			e.printStackTrace();
			res.setStatus(400);
			order = null;
		}
		return order;
	}

	@PutMapping("order/{modId}")
	public Order updateExercise(

			@RequestBody Order order, @PathVariable int modId, HttpServletResponse res) {

		if (order == null) {
			res.setStatus(404);
		} else {
			order = ordServ.updateOrder(order, modId);
		}
		return order;
	}

	@DeleteMapping("order/{id}")
	public void deleteFilm(

			@PathVariable int id, HttpServletResponse res) {
		try {
			if (ordServ.destroyOrder(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}

	}	
	
//	@GetMapping("order/{orderId}")
//	public Order showOrderByIdAndUsername(Principal principal, @PathVariable int id, HttpServletResponse res) {
//
//		if (ordServ.findCustomerOrder(principal.getName(), id) == null) {
//			res.setStatus(404);
//		} else {
//
//			return ordServ.findCustomerOrder(principal.getName(), id);
//		}
//		return null;
//	}

}