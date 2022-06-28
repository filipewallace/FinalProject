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

class DeveloperTest {
	private static EntityManagerFactory emf;

	private EntityManager em;

	private Developer developer;

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
		developer = em.find(Developer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		developer = null;
	}

	@Test
	void test_Developer_entity_mapping() {
		assertNotNull(developer);
		assertEquals("Pony", developer.getName());
	}

	@Test
	void test_Dev_Game_mapping() {
		assertNotNull(developer);
		assertNotNull(developer.getGames());
		assertTrue(developer.getGames().size() > 0);

	}
}
