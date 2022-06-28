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

class ModTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Mod mod;

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
		mod = em.find(Mod.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		mod = null;
	}

	@Test
	void test_Mod_entity_mapping() {
		assertNotNull(mod);
		assertEquals("Rani Saves the Princess", mod.getTitle());
	}

	@Test
	void test_Game_EsrbRating_mapping() {
		assertNotNull(mod);
		assertNotNull(mod.getGame());
		assertEquals("Elden Ring", mod.getGame().getName());

	}
	
	@Test
	void test_user_mod_mapping() {
		assertNotNull(mod);
		assertNotNull(mod.getUser());
		assertEquals("John", mod.getUser().getFirstName());
	}
	
	
	@Test
	void test_ModMedias_mod_mapping() {
		assertNotNull(mod);
		assertNotNull(mod.getModMedias());
		assertTrue(mod.getModMedias().size() > 0);
	}
	
	@Test
	void test_mod_Post_mapping() {
		assertNotNull(mod);
		assertNotNull(mod.getPosts());
		assertTrue(mod.getPosts().size() > 0);

	}
	
	@Test
	void test_Mod_Order_mapping() {
		assertNotNull(mod);
		assertNotNull(mod.getOrders());
		assertTrue(mod.getOrders().size() > 0);

	}
	
	@Test
	void test_Mod_User_ManyToMany_mapping() {
		assertNotNull(mod);
		assertNotNull(mod.getUsers());
		assertTrue(mod.getUsers().size() > 0);

	}

}
