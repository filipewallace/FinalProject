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

import com.skilldistillery.mod.entities.Mod;
import com.skilldistillery.mod.services.ModService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ModController {

	@Autowired
	private ModService modServ;

	@GetMapping("/mods/{id}")
	public Mod findModById(@PathVariable Integer id, HttpServletResponse res) {

		Mod mod = modServ.getModById(id);
		if (mod == null) {

			res.setStatus(404);
		}

		return mod;
	}

	@GetMapping("mods")
	public List<Mod> modIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			List<Mod> mods = modServ.modIndex();
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

	@PostMapping("mods")
	public Mod createNewMod(HttpServletRequest req, HttpServletResponse res, @RequestBody Mod mod,
			Principal principal) {

		try {
			mod = modServ.createMod(mod);
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

	@PutMapping("mods/{id}")
	public Mod updateModInfo(@PathVariable int id, @RequestBody Mod mod, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			mod = modServ.updateMod(id, mod);
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
	

	@DeleteMapping("mods/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {

		try {
			if (modServ.destroyMod(id)) {
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
