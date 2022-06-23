package com.skilldistillery.mod.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Persistence;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Review {

	@EmbeddedId
	private CompositeIDReview id;

	@ManyToOne
	@JoinColumn(name = "user_id") // DB column name
	@MapsId(value = "userId") // Field in ID class
	private User user;

	@ManyToOne
	@JoinColumn(name = "mod_id") // DB column
	@MapsId(value = "modId") // Field in ID class
	private Mod mod;

	private int score;

	private String opinion;

	@Column(name = "review_date")
	@CreationTimestamp
	private LocalDateTime reviewDate;

	public Review() {
		super();
	}

	public CompositeIDReview getId() {
		return id;
	}

	public void setId(CompositeIDReview id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Mod getMod() {
		return mod;
	}

	public void setMod(Mod mod) {
		this.mod = mod;
	}

	public boolean modHasReview(Integer userId, Integer modId) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
		EntityManager em = emf.createEntityManager();

		boolean reviewed = false;
		User user = em.find(User.class, userId);
		Mod mod = em.find(Mod.class, modId);
		if (user != null && mod != null) {
			CompositeIDReview id = new CompositeIDReview(userId, modId);
			Review review = new Review();
			review.setId(id);
			review.setUser(user);
			review.setMod(mod);
			em.persist(review);
			reviewed = true;
		}
		return reviewed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Review [score=" + score + ", opinion=" + opinion + ", reviewDate=" + reviewDate + "]";
	}

}
