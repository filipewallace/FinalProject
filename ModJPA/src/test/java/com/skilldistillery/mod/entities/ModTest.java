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

}
