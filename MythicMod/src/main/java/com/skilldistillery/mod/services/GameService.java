package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Game;

public interface GameService {

	public Game getGameById(int gameId);

//	--------------------------new------------------------------------------

	public Game showGame(String gameName, int gameId);

	public List<Game> gameIndex();

	public Game createGame(Game game);

	public Game updateGame(String gameName, int gameId, Game game);

	public boolean destroyGame(int gameId);

}
