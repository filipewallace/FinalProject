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

import com.skilldistillery.mod.entities.Review;
import com.skilldistillery.mod.services.ReviewService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ReviewController {

	@Autowired
	private ReviewService reviewServ;
	
	
	//
	@GetMapping("review/{modReviewId}/mod")
	public List<Review> modReviews(@PathVariable Integer modReviewId, HttpServletResponse res){
		System.out.println("REVIEW ID:"+modReviewId);
		
		List<Review> userReviews = reviewServ.listModReviews(modReviewId);
		if (userReviews == null) {
			res.setStatus(404);
		}
		return userReviews;
	}
	
	
	@GetMapping("review/{userReviewId}/user")
	public List<Review> userReviews(@PathVariable Integer userReviewId, HttpServletResponse res){
	
		
		List<Review> userReviews = reviewServ.listUserReviews(userReviewId);
		if (userReviews == null) {
			res.setStatus(404);
		}
		return userReviews;
	}
	
	
//	@GetMapping("meetups/{meetupId}/attendees/{dogId}")
//	public MeetupAttendance getAttendee(
//			@PathVariable Integer meetupId, @PathVariable Integer dogId, HttpServletResponse res){
//		MeetupAttendance attend = attendSvc.getAttendance(meetupId, dogId);
//		if (attend == null) {
//			res.setStatus(404);
//		}
//		return attend;
//	}
//	
	@PostMapping("review/{modId}/user/{userId}")
	public Review addReview(
			Principal principal,
			@RequestBody Review review, 
			@PathVariable Integer userId,
			@PathVariable Integer modId,
			HttpServletRequest req,
			HttpServletResponse res
	) {
		
		try {
			review = reviewServ.writeReview(userId, modId, review);
			if (review == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			review = null;
		}
		return review;
	}
	
	@PutMapping("review/{modId}/user/{userId}")
	public Review updateReview(
			Principal principal,
			@RequestBody Review review, 
			@PathVariable Integer userId,
			@PathVariable Integer modId,
			HttpServletRequest req,
			HttpServletResponse res
	) {
		
		try {
			review = reviewServ.updateReview(userId, modId, review);
			if (review == null) {
				res.setStatus(404);
			}
			else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			review = null;
		}
		return review;
	}
	
	@DeleteMapping("review/{modId}/user/{userId}")
	public void destroy(Principal principal,
			@PathVariable Integer userId,
			@PathVariable Integer modId,
			HttpServletRequest req,
			HttpServletResponse res) {

		try {
			if (reviewServ.deleteReview(userId, modId)) {
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