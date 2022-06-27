package com.skilldistillery.mod.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.CompositeIDReview;
import com.skilldistillery.mod.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	List<Review> findByUser_Username(String username);
	
	Review findByMod_IdAndUser_Username(int modId, String username);

	boolean existsById(CompositeIDReview reviewId);
	
	

}
