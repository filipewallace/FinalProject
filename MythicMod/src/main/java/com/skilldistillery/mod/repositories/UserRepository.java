package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);


	
	

}
