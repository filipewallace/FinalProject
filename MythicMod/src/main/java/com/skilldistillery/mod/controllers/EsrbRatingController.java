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

import com.skilldistillery.mod.entities.EsrbRating;
import com.skilldistillery.mod.services.EsrbRatingService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class EsrbRatingController {
	
	@Autowired
	private EsrbRatingService esrbServ;

	@GetMapping("/rating/{id}")
	public EsrbRating findEsrbRatingById(@PathVariable Integer id, HttpServletResponse res) {

		EsrbRating esrbRating = esrbServ.show(id);
		if (esrbRating == null) {

			res.setStatus(404);
		}

		return esrbRating;
	}

	@GetMapping("rating")
	public List<EsrbRating> ratingIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {
	
		System.out.println("IN ESRB RATING INDEX CONTROLLER");
		try {
			List<EsrbRating> esrbRating = esrbServ.index();
			if (esrbRating == null) {
				res.setStatus(404);
			}
			return esrbRating;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}

	@PostMapping("rating")
	public EsrbRating createNewRating(HttpServletRequest req, HttpServletResponse res,
			@RequestBody EsrbRating esrbRating, Principal principal) {

		try {
			esrbRating = esrbServ.create(esrbRating);
			if (esrbRating == null) {

				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(esrbRating.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid ESRB Rating JSON");
			res.setStatus(400);
			esrbRating = null;
		}
		return esrbRating;
	}

	@PutMapping("rating/{id}")
	public EsrbRating updateRating(@PathVariable int id, @RequestBody EsrbRating esrbRating, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			esrbRating = esrbServ.update(id, esrbRating);
			if (esrbRating == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			esrbRating = null;
		}
		return esrbRating;

	}

	@DeleteMapping("rating/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {

		try {
			if (esrbServ.destroy(id)) {
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
