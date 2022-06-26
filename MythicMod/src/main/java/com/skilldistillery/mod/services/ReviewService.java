package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Review;

public interface ReviewService {
	
	public List<Review> index(String username);
	
	public Review show(String username, int rid);
	
	public Review create(String username, Review review);
	
	public Review update(String username, int rid, Review reveiw);
	
	public boolean destroy(String username, int rid);

	Review writeReview(String modTitle, int userId, int modId, Review review);
	
	public List<Review> listUserReviews(int modId);

}
