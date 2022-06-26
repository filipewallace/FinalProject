package com.skilldistillery.mod.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.CompositeIDReview;
import com.skilldistillery.mod.entities.Mod;
import com.skilldistillery.mod.entities.Review;
import com.skilldistillery.mod.entities.User;
import com.skilldistillery.mod.repositories.ModRepository;
import com.skilldistillery.mod.repositories.ReviewRepository;
import com.skilldistillery.mod.repositories.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModRepository modRepo;

	@Override
	public Review writeReview(String modTitle, int userId, int modId, Review review) {
		Mod mod = modRepo.findByTitleAndId(modTitle, modId);
		User user = userRepo.queryById(userId);
		if (mod != null && user != null) {
			CompositeIDReview reviewId = new CompositeIDReview(userId, modId);
			review.setId(reviewId);
			review.setMod(mod);
			review.setUser(user);
			return reviewRepo.saveAndFlush(review);
		}
		return null;
	}

	@Override
	public List<Review> listUserReviews(int reviewId) {
		
		List<Review> modReviews = new ArrayList<>();
		List<Review> userReviews = reviewRepo.findAll();
		for (Review review : userReviews) {
			if (review.getMod().getId() == reviewId) {
				modReviews.add(review);
				
			}
			
		}
		return modReviews;
//		if (reviewRepo.findById(modId) == null) {
//			return null;
//		}
//		return reviewRepo.findAll(modId);
	}

	@Override
	public Review create(String username, Review review) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			review.setUser(user);
			return reviewRepo.saveAndFlush(review);
		} else {
			return null;
		}

	}

	@Override
	public Review update(String username, int rid, Review reveiw) {
		Review managed = reviewRepo.findByIdAndUser_Username(rid, username);
		if (managed != null) {
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
		if (managed != null) {
			reviewRepo.deleteById(rid);
			return true;
		}

		return false;
	}

	@Override
	public List<Review> index(String username) {
		if (reviewRepo.findByUser_Username(username) == null) {
			return null;
		}
		return reviewRepo.findByUser_Username(username);
	}

	@Override
	public Review show(String username, int rid) {
		Review result = reviewRepo.findByIdAndUser_Username(rid, username);
		if (result == null) {
			return null;
		}
		return result;
	}

}
