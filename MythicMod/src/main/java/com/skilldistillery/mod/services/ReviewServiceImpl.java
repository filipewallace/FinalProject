package com.skilldistillery.mod.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.mod.entities.Review;
import com.skilldistillery.mod.entities.User;
import com.skilldistillery.mod.repositories.ReviewRepository;
import com.skilldistillery.mod.repositories.UserRepository;

public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
	private UserRepository userRepo;

	
	@Override
	public Review create(String username, Review review) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			review.setUser(user);
			return reviewRepo.saveAndFlush(review);
		} else {
			return null;
		}
		
	
	}

	@Override
	public Review update(String username, int rid, Review reveiw) {
		Review managed = reviewRepo.findByIdAndUser_Username(rid, username);
		if(managed != null) {
			managed.setScore(reveiw.getScore());
			managed.setOpinion(reveiw.getOpinion());
			managed.setUser(reveiw.getUser());
			managed.setMod(reveiw.getMod());
			managed.setReviewDate(reveiw.getReviewDate());
			reviewRepo.saveAndFlush(managed);
			return managed;
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int rid) {
		Review managed = reviewRepo.findByIdAndUser_Username(rid, username);
		if(managed != null) {
			reviewRepo.deleteById(rid);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Review> index(String username) {
		if(reviewRepo.findByUser_Username(username) == null) {
			return null;
		}
		return reviewRepo.findByUser_Username(username);
	}

	@Override
	public Review show(String username, int rid) {
		Review result = reviewRepo.findByIdAndUser_Username(rid, username);
		if(result == null) {
			return null;
		}
		return result;
	}

}
