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

import com.skilldistillery.mod.entities.Category;
import com.skilldistillery.mod.services.CategoryService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class CategoryController {

	@Autowired
	private CategoryService catServ;

	@GetMapping("/category/{id}")
	public Category findCategoryById(@PathVariable Integer id, HttpServletResponse res) {

		Category category = catServ.getCategoryById(id);
		if (category == null) {

			res.setStatus(404);
		}

		return category;
	}

	@GetMapping("category")
	public List<Category> categoryIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			List<Category> categories = catServ.categoryIndex();
			if (categories == null) {
				res.setStatus(404);
			}
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}

	@PostMapping("category")
	public Category createNewCategory(HttpServletRequest req, HttpServletResponse res, @RequestBody Category category,
			Principal principal) {

		try {
			category = catServ.createCategory(category);
			if (category == null) {

				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(category.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			category = null;
		}
		return category;
	}

	@PutMapping("category/{id}")
	public Category updateCategoryInfo(@PathVariable int id, @RequestBody Category category,
			HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			category = catServ.updateCategory(id, category);
			if (category == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			category = null;
		}
		return category;

	}

	@DeleteMapping("category/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id,
			Principal principal) {

		try {
			if (catServ.destroyCategory(id)) {
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
