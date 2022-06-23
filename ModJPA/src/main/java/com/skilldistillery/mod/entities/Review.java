package com.skilldistillery.mod.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;

//@Entity
//public class Review {
//	
//	private int score;
//	
//	private String opinion;
//	
//	@Column(name = "review_date")
//	@CreationTimestamp
//	private LocalDateTime reviewDate;
//
//	public Review() {
//		super();
//	}
//
//	public int getScore() {
//		return score;
//	}
//
//	public void setScore(int score) {
//		this.score = score;
//	}
//
//	public String getOpinion() {
//		return opinion;
//	}
//
//	public void setOpinion(String opinion) {
//		this.opinion = opinion;
//	}
//
//	public LocalDateTime getReviewDate() {
//		return reviewDate;
//	}
//
//	public void setReviewDate(LocalDateTime reviewDate) {
//		this.reviewDate = reviewDate;
//	}
//
//	@Override
//	public String toString() {
//		return "Review [score=" + score + ", opinion=" + opinion + ", reviewDate=" + reviewDate + "]";
//	}
//	
//	
//
//}
