package com.skilldistillery.mod.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Embeddable
public class CompositeIDReview implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "mod_id")
	private Integer modId;

	public CompositeIDReview() {
		super();
	}

	public CompositeIDReview(Integer userId, Integer modId) {
		super();

		this.userId = userId;
		this.modId = modId;
	}

	public boolean modReview(int userId, int modId) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
		EntityManager em = emf.createEntityManager();

		boolean reviewed = false;
		User user = em.find(User.class, userId);
		Mod mod = em.find(Mod.class, modId);
		if (mod != null && user != null) {
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getModId() {
		return modId;
	}

	public void setModId(Integer modId) {
		this.modId = modId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
