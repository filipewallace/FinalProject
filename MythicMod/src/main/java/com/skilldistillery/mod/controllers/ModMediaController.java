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
import com.skilldistillery.mod.services.ModMediaService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ModMediaController {
	
	
	@Autowired
	private ModMediaService modMediaSvc;
	
	
	
	@GetMapping("modMedia/{id}")
	public ModMedia findModById(@PathVariable Integer id, HttpServletResponse res) {

		ModMedia modM = modMediaSvc.show(id);
		if (modM == null) {

			res.setStatus(404);
		}
		return modM;
	}
	
	
	
	@GetMapping("modMedia")
	public List<ModMedia> modIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			List<ModMedia> mods = modMediaSvc.index();
			if (mods == null) {
				res.setStatus(404);
			}
			return mods;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}
	
	@PostMapping("modMedia")
	public ModMedia createNewMod(HttpServletRequest req, HttpServletResponse res, @RequestBody ModMedia mod,
			Principal principal) {

		try {
			mod = modMediaSvc.create(mod);
			if (mod == null) {

				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(mod.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			mod = null;
		}
		return mod;
	}
	
	
	
	@PutMapping("modMedia/{id}")
	public ModMedia updateModInfo(@PathVariable int id, @RequestBody ModMedia mod, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			mod = modMediaSvc.update(id, mod);
			if (mod == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			mod = null;
		}
		return mod;

	}
	
	
	
	
	@DeleteMapping("modMedia/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {

		try {
			if (modMediaSvc.destroy(id)) {
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
