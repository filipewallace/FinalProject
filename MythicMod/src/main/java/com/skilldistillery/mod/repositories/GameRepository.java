package com.skilldistillery.mod.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

	Game findByName(String name);

	Game findByNameAndId(String name, int gameId);

	List<Game> findByPlatform_Id(int platfromId);

	List<Game> findByDev_Id(int developerId);

	List<Game> findByPublisher_Id(int publisherId);

	List<Game> findByRating_Id(int esrbRatingId);
	
	List<Game> findByCategories_Id(int categoryId);
		
}
