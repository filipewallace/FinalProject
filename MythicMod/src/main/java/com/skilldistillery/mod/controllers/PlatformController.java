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

import com.skilldistillery.mod.entities.Platform;
import com.skilldistillery.mod.services.PlatformService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class PlatformController {

	@Autowired
	private PlatformService platServ;

	@GetMapping("/platform/{id}")
	public Platform findPlatformById(@PathVariable Integer id, HttpServletResponse res) {

		Platform platform = platServ.show(id);
		if (platform == null) {

			res.setStatus(404);
		}

		return platform;
	}

	@GetMapping("platform")
	public List<Platform> findAllPlatforms(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			List<Platform> platforms = platServ.index();
			if (platforms == null) {
				res.setStatus(404);
			}
			return platforms;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}
	
	@PostMapping("platform")
	public Platform createPlatform(HttpServletRequest req, HttpServletResponse res, @RequestBody Platform platform,
			Principal principal) {

		try {
			platform = platServ.create(platform);
			if (platform == null) {

				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(platform.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Platform JSON");
			res.setStatus(400);
			platform = null;
		}
		return platform;
	
	}
	

	@PutMapping("platform/{id}")
	public Platform updatePlatform(@PathVariable int id, @RequestBody Platform platform, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			platform = platServ.update(id, platform);
			if (platform == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			platform = null;
		}
		return platform;

	}
	
	@DeleteMapping("platform/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {

		try {
			if (platServ.destroy(id)) {
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
