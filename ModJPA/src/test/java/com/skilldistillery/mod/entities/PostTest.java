package com.skilldistillery.mod.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostTest {

private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Post post;

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
		post = em.find(Post.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		post = null;
	}

	@Test	
	void test_Post_entity_mapping() {
		assertNotNull(post);
		assertEquals("Mod Okay", post.getTitle());
	}
	
	
	@Test	
	void test_Post_User_mapping() {
		assertNotNull(post);
		assertNotNull(post.getUser());
		assertEquals("John", post.getUser().getFirstName());
	}
	
	
	@Test	
	void test_Post_Mod_mapping() {
		assertNotNull(post);
		assertNotNull(post.getMod());
		assertEquals("Processor Intel Potato Core", post.getMod().getRequirements());
	}


}
