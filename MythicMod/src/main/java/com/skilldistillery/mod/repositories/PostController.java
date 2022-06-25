package com.skilldistillery.mod.repositories;

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

import com.skilldistillery.mod.entities.Post;
import com.skilldistillery.mod.services.PostService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class PostController {

	@Autowired
	private PostService postServ;
	
	@GetMapping("/post/{id}")
	public Post findPostById(@PathVariable Integer id, HttpServletResponse res) {

		Post post = postServ.getPostById(id);
		if (post == null) {

			res.setStatus(404);
		}

		return post;
	}
	
	@GetMapping("post")
	public List<Post> postIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			List<Post> posts = postServ.postIndex();
			if (posts == null) {
				res.setStatus(404);
			}
			return posts;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}
	
	@PostMapping("post")
	public Post createNewPost(HttpServletRequest req, HttpServletResponse res, @RequestBody Post post,
			Principal principal) {

		try {
			post = postServ.createPost(post);
			if (post == null) {

				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(post.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid JSON");
			res.setStatus(400);
			post = null;
		}
		return post;
	}
	
	@PutMapping("post/{id}")
	public Post updatePostInfo(@PathVariable int id, @RequestBody Post post, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			post = postServ.updatePost(id, post);
			if (post == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			post = null;
		}
		return post;

	}
	
	@DeleteMapping("post/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {

		try {
			if (postServ.destroyPost(id)) {
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
