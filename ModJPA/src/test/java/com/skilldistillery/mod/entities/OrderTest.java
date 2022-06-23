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

class OrderTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Order order;

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
		order = em.find(Order.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		order = null;
	}

	@Test	
	void test_Order_entity_mapping() {
		assertNotNull(order);
		assertEquals("Joe Doe", order.getName());
	}

}
