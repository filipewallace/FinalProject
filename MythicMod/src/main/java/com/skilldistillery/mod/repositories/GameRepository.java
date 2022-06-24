package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
	
	Game findByName(String name);
	
	Game findByNameAndId(String name, int gameId);

}
