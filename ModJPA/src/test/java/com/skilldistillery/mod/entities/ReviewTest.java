package com.skilldistillery.mod.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReviewTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Review reviewTest1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		emf = Persistence.createEntityManagerFactory("ModJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		CompositeIDReview jid1 = new CompositeIDReview();
		jid1.setUserId(2);
		jid1.setModId(1);
		reviewTest1 = em.find(Review.class, jid1);

	}

	@AfterEach
	void tearDown() throws Exception {

		em.close();
		reviewTest1 = null;

	}

	@Test
	@DisplayName("testing review composite mapping between user and user")
	void testing_job_skill_composite_mapping_between_skill_and_resume() {

		assertNotNull(reviewTest1);

		assertNotNull(reviewTest1.getUser().getReviews());
		assertNotNull(reviewTest1.getMod().getReviews());

		assertTrue(reviewTest1.getUser().getReviews().size() > 0);
		assertTrue(reviewTest1.getMod().getReviews().size() > 0);

	}
}
