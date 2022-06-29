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
	public Review writeReview(String username, int modId, Review review) {
		Mod mod = modRepo.queryById(modId);
		User user = userRepo.findByUsername(username);
		if (mod != null && user != null) {
			CompositeIDReview reviewId = new CompositeIDReview(user.getId(), modId);
			review.setId(reviewId);
			review.setMod(mod);
			review.setUser(user);
			return reviewRepo.saveAndFlush(review);
		}
		return null;
	}
	
	
	@Override
	public Review updateReview(String username, int modId, Review review) {
		Mod mod = modRepo.queryById(modId);
		User user = userRepo.findByUsername(username);
		if (mod != null && user != null) {
			CompositeIDReview reviewId = new CompositeIDReview(user.getId(), modId);
			review.setId(reviewId);
			review.setMod(mod);
			review.setUser(user);
			return reviewRepo.saveAndFlush(review);
		}
		return null;
	}

	public Boolean deleteReview(String username, Integer modId) {
			
		
		Review review= reviewRepo.findByMod_IdAndUser_Username(modId, username);
		
		if (review != null) {
			
			reviewRepo.delete(review);

			boolean deleted = !reviewRepo.existsById(review.getId());
			return deleted;
		}

		return false;

	}

	@Override
	public List<Review> listModReviews(int reviewId) {

		List<Review> modReviews = new ArrayList<>();
		List<Review> reviewList = reviewRepo.findAll();
		for (Review review : reviewList) {
			if (review.getMod().getId() == reviewId) {
				modReviews.add(review);
				System.out.println(review);
			}

		}
		return modReviews;
	}

	@Override
	public List<Review> listUserReviews(int reviewId) {

		System.out.println("REVIEW ID:" + reviewId);

		List<Review> userReviews = new ArrayList<>();
		List<Review> reviewList = reviewRepo.findAll();
		for (Review review : reviewList) {
			if (review.getUser().getId() == reviewId) {

				System.out.println("REVIEW: " + review);

				userReviews.add(review);

			}

		}
		return userReviews;
	}

//	@Override
//	public Review create(String username, Review review) {
//		User user = userRepo.findByUsername(username);
//		if (user != null) {
//			review.setUser(user);
//			return reviewRepo.saveAndFlush(review);
//		} else {
//			return null;
//		}
//
//	}
//
//	@Override
//	public Review update(String username, int rid, Review reveiw) {
//		Review managed = reviewRepo.findByIdAndUser_Username(rid, username);
//		if (managed != null) {
//			managed.setScore(reveiw.getScore());
//			managed.setOpinion(reveiw.getOpinion());
//			managed.setUser(reveiw.getUser());
//			managed.setMod(reveiw.getMod());
//			managed.setReviewDate(reveiw.getReviewDate());
//			reviewRepo.saveAndFlush(managed);
//			return managed;
//		}
//		return null;
//	}

//	@Override
//	public boolean destroy(String username, int rid) {
//		Review managed = reviewRepo.findByIdAndUser_Username(rid, username);
//		if (managed != null) {
//			reviewRepo.deleteById(rid);
//			return true;
//		}
//
//		return false;
//	}

//	@Override
//	public boolean destroy( int rid) {
//		Review managed = reviewRepo.findById(rid);
//		if (managed != null) {
//			reviewRepo.deleteById(rid);
//			return true;
//		}
//
//		return false;
//	}

//	@Override
//	public List<Review> index(String username) {
//		if (reviewRepo.findByUser_Username(username) == null) {
//			return null;
//		}
//		return reviewRepo.findByUser_Username(username);
//	}
//
//	@Override
//	public Review show(String username, int rid) {
//		Review result = reviewRepo.findByIdAndUser_Username(rid, username);
//		if (result == null) {
//			return null;
//		}
//		return result;
//	}
//
//	@Override
//	public boolean destroy(int rid) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
