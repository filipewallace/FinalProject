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

class UserTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}

	@Test
	void test_User_Mod_mapping() {
		assertNotNull(user);
		assertNotNull(user.getMods());
		assertTrue(user.getMods().size() > 0);

	}

	@Test
	void test_User_ModMedia_mapping() {
		assertNotNull(user);
		assertNotNull(user.getModMedias());
		assertTrue(user.getModMedias().size() > 0);

	}

	@Test
	void test_User_Order_mapping() {
		assertNotNull(user);
		assertNotNull(user.getOrders());
		assertTrue(user.getOrders().size() > 0);

	}

}
