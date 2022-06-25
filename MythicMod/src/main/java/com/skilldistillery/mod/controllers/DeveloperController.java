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

import com.skilldistillery.mod.entities.Developer;
import com.skilldistillery.mod.services.DeveloperService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class DeveloperController {

	@Autowired
	private DeveloperService devServ;

	@GetMapping("/developers/{id}")
	public Developer findDeveloperById(@PathVariable Integer id, HttpServletResponse res) {

		Developer developer = devServ.getDeveloperById(id);
		if (developer == null) {

			res.setStatus(404);
		}

		return developer;
	}

	@GetMapping("developers")
	public List<Developer> developerIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			List<Developer> developers = devServ.developerIndex();
			if (developers == null) {
				res.setStatus(404);
			}
			return developers;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}

	@PostMapping("developers")
	public Developer createNewDeveloper(HttpServletRequest req, HttpServletResponse res,
			@RequestBody Developer developer, Principal principal) {

		try {
			developer = devServ.createDeveloper(developer);
			if (developer == null) {

				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(developer.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			developer = null;
		}
		return developer;
	}

	@PutMapping("developers/{id}")
	public Developer updateDeveloperInfo(@PathVariable int id, @RequestBody Developer dev, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			dev = devServ.updateDeveloper(id, dev);
			if (dev == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			dev = null;
		}
		return dev;

	}

	@DeleteMapping("developers/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {

		try {
			if (devServ.destroyDeveloper(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}

	}

}
