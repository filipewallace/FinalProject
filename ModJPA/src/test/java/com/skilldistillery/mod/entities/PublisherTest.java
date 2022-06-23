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

class PublisherTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Publisher publisher;

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
		publisher = em.find(Publisher.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		publisher = null;
	}

	@Test	
	void test_Publisher_entity_mapping() {
		assertNotNull(publisher);
		assertEquals("Cupcake", publisher.getName());
	}

}