package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByGenre(String genre);

	Category findByGenreAndId(String genre, int genreId);

}
