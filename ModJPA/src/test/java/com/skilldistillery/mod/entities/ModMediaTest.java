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

class ModMediaTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private ModMedia modMedia;

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
		modMedia = em.find(ModMedia.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		modMedia = null;
	}

	@Test	
	void test_ModMedia_entity_mapping() {
		assertNotNull(modMedia);
		assertEquals("test", modMedia.getDescription());
	}
	
	@Test
	void test_ModMedia_mod_mapping() {
		assertNotNull(modMedia);
		assertNotNull(modMedia.getMod());
		assertEquals("Processor Intel Potato Core", modMedia.getMod().getRequirements());
	}
	
	
	@Test	
	void test_ModMedia_User_mapping() {
		assertNotNull(modMedia);
		assertNotNull(modMedia.getUser());
		assertEquals("John", modMedia.getUser().getFirstName());
	}

}