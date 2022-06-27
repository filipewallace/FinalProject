package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Game;

public interface GameService {

	public Game getGameById(int gameId);

	public Game showGame(String gameName, int gameId);

	public List<Game> gameIndex();

	public Game createGame(Game game);

	public Game updateGame(int gameId, Game game);

	public boolean destroyGame(int gameId);

	public List<Game> showGamesByPlatform(Integer platformId);

	public List<Game> showGamesByDeveloper(Integer developerId);

	public List<Game> showGamesByPublisher(Integer publisherId);

	public List<Game> showGamesByRating(Integer ratingId);
	
	public List<Game> showGamesByCategory(Integer categoryId);

}
