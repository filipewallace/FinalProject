package com.skilldistillery.mod.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mod.entities.User;
import com.skilldistillery.mod.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class UserController {

	@Autowired
	private UserService userService;
	
	// SMOKE TEST ONLY, DELETE/COMMENT OUT LATER
	@GetMapping("test/users/{userId}")
	public User getUserForTest(
	  @PathVariable Integer userId,
	  HttpServletResponse res
	) {
	  User user = userService.getUserById(userId);
	  if (user == null) {
	    res.setStatus(404);
	  }
	  return user;
	}
	
//	------------------------new----------------------------------------------------------
	
	@PostMapping("user")
	public User addNewUser(
			
			@RequestBody User user, 
			HttpServletRequest req, 
			HttpServletResponse res) {
		
		try {
			user = userService.createUser(user);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}
		return user;
	}
}
