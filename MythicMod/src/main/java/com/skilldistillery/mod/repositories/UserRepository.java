package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

	User findById(int userId);

	User queryById(int userId);

	User findByEmail(String email);

	User findByFirstNameAndLastName(String firstName, String lastName);
	
	

}
