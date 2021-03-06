package com.skilldistillery.mod.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EsrbRatingTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private EsrbRating esrbRating;

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
		esrbRating = em.find(EsrbRating.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		esrbRating = null;
	}

	@Test
	void test_EsrbRating_entity_mapping() {
		assertNotNull(esrbRating);
		assertEquals("Everyone", esrbRating.getName());
	}

	@Test
	void test_Game_esrbRating_mapping() {
		assertNotNull(esrbRating);
		assertNotNull(esrbRating.getGames());
		assertTrue(esrbRating.getGames().size() > 0);

	}
}
