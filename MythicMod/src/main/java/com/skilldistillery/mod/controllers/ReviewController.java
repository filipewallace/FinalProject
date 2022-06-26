package com.skilldistillery.mod.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("review/{reviewId}")
	public List<Review> reviews(@PathVariable Integer reviewId, HttpServletResponse res){
		List<Review> userReviews = reviewServ.listUserReviews(reviewId);
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
//	@PostMapping("meetups/{meetupId}/attendees/{dogId}")
//	public MeetupAttendance addAttendee(
//			Principal principal,
//			@RequestBody MeetupAttendance attendance, 
//			@PathVariable Integer meetupId,
//			@PathVariable Integer dogId,
//			HttpServletRequest req,
//			HttpServletResponse res
//	) {
//		try {
//		    attendance = attendSvc.attendMeetup(principal.getName(), meetupId, dogId, attendance);
//			if (attendance == null) {
//				res.setStatus(404);
//			}
//			else {
//				res.setStatus(201);
//				StringBuffer url = req.getRequestURL();
//				res.setHeader("Location", url.toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			res.setStatus(400);
//			attendance = null;
//		}
//		return attendance;
//	}
	
}