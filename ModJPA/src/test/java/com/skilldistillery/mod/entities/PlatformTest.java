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

class PlatformTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Platform platform;

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
		platform = em.find(Platform.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		platform = null;
	}

	@Test	
	void test_Platform_entity_mapping() {
		assertNotNull(platform);
		assertEquals("PC", platform.getName());
	}
	
	@Test
	void test_Game_Category_mapping() {
		assertNotNull(platform);
		assertNotNull(platform.getGames());
		assertTrue(platform.getGames().size() > 0);

	}

}
