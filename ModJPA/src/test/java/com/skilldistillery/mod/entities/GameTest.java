package com.skilldistillery.mod.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
	private static EntityManagerFactory emf;

	private EntityManager em;

	private Game game;

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
		game = em.find(Game.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		game = null;
	}

	@Test
	void test_Game_entity_mapping() {
		assertNotNull(game);
		assertEquals("Elden Ring", game.getName());
	}

	@Test
	void test_Game_Category_mapping() {
		assertNotNull(game);
		assertNotNull(game.getCategories());
		assertTrue(game.getCategories().size() > 0);

	}

	@Test
	void test_Game_Dev_mapping() {
		assertNotNull(game);
		assertNotNull(game.getDev());
		assertEquals("Pony", game.getDev().getName());

	}

	@Test
	void test_Game_Publisher_mapping() {
		assertNotNull(game);
		assertNotNull(game.getPublisher());
		assertEquals("Cupcake", game.getPublisher().getName());

	}

	@Test
	void test_Game_Platform_mapping() {
		assertNotNull(game);
		assertNotNull(game.getPlatform());
		assertEquals("PC", game.getPlatform().getName());

	}

	@Test
	void test_Game_EsrbRating_mapping() {
		assertNotNull(game);
		assertNotNull(game.getRating());
		assertEquals("Everyone", game.getRating().getName());

	}

	@Test
	void test_Game_Mod_mapping() {
		assertNotNull(game);
		assertNotNull(game.getMods());
		assertTrue(game.getMods().size() > 0);

	}
}
