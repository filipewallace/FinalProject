package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Review;

public interface ReviewService {
	
	Review writeReview(String uername, int modId, Review review);
	
	public List<Review> listModReviews(int reviewId);

	public List<Review> listUserReviews(int reviewId);
	
	Review updateReview(String username, int modId, Review review);	
	
	public Boolean deleteReview(String username, Integer modId);

	

	

	

	

}
