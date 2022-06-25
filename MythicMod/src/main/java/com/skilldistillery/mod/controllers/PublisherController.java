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

import com.skilldistillery.mod.entities.ModMedia;
import com.skilldistillery.mod.entities.Publisher;
import com.skilldistillery.mod.services.PublisherService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class PublisherController {
	
	@Autowired
	private PublisherService pubServ;
	
	@GetMapping("publisher/{id}")
	public Publisher findPubById(@PathVariable Integer id, HttpServletResponse res) {

		Publisher p = pubServ.show(id);
		if (p == null) {

			res.setStatus(404);
		}
		return p;
	}
	
	@GetMapping("publisher")
	public List<Publisher> pubIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			List<Publisher> publishers = pubServ.index();
			if (publishers == null) {
				res.setStatus(404);
			}
			return publishers;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}
	
	@PostMapping("publisher")
	public Publisher createPublisher(HttpServletRequest req, HttpServletResponse res, @RequestBody Publisher publisher,
			Principal principal) {

		try {
			publisher = pubServ.create(publisher);
			if (publisher== null) {

				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(publisher.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			publisher = null;
		}
		return publisher;
	}
	
	@PutMapping("publisher/{id}")
	public Publisher updatePubInfo(@PathVariable int id, @RequestBody Publisher publisher, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			publisher = pubServ.update(id, publisher);
			if (publisher == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			publisher = null;
		}
		return publisher;

	}
	
	@DeleteMapping("publisher/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {

		try {
			if (pubServ.destroy(id)) {
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
