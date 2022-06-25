package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

	Developer findByName(String name);

	Developer findByNameAndId(String name, int id);

}
