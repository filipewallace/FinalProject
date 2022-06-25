package com.skilldistillery.mod.controllers;

import java.security.Principal;
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

import com.skilldistillery.mod.entities.User;
import com.skilldistillery.mod.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class UserController {

	@Autowired
	private UserService userService;

	// SMOKE TEST ONLY, DELETE/COMMENT OUT LATER
	/*
	 * @GetMapping("test/users/{userId}") public User getUserForTest(
	 * 
	 * @PathVariable Integer userId, HttpServletResponse res ) { User user =
	 * userService.getUserById(userId); if (user == null) { res.setStatus(404); }
	 * return user; }
	 */

//	------------------------new----------------------------------------------------------

	@DeleteMapping("user/{id}")
	public void deleteUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		try {
			if (userService.destroyUser(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}

	}

	@PutMapping("user/{id}")
	public User update(HttpServletResponse res, HttpServletRequest req, @PathVariable int id, Principal principal,
			@RequestBody User user) {
		user = userService.updateUser(id, user);
		if (user == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}

		return user;

	}
	
	
	@PostMapping("user")
	public User createNewUser(HttpServletRequest req, HttpServletResponse res, @RequestBody User user,
			Principal principal) {

		try {
			user = userService.createUser(user);
			if (user == null) {

				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(user.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			user = null;
		}
		return user;
	}
	
	

	@GetMapping("user/{id}")
	public User Show(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {
		User user = userService.showUser(id);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
	//COMMENT TEST
	@GetMapping("user")
	public List<User> userIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			List<User> users = userService.userIndex();
			if (users == null) {
				res.setStatus(404);
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}

}
