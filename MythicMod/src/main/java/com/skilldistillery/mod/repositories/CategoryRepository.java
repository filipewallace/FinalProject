package com.skilldistillery.mod.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Category findById(int id);

}
