package com.skilldistillery.mod.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.Game;
import com.skilldistillery.mod.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepo;

	@Override
	public Game getGameById(int gameId) {
		Optional<Game> gameOpt = gameRepo.findById(gameId);
		Game game = null;
		if (gameOpt.isPresent()) {
			game = gameOpt.get();
		}
		return game;
	}

	@Override
	public Game showGame(String gameName, int gameId) {

		return gameRepo.findByNameAndId(gameName, gameId);
	}

	@Override
	public List<Game> gameIndex() {

		return gameRepo.findAll();
	}

	@Override
	public Game createGame(Game game) {

		return gameRepo.saveAndFlush(game);

	}

	@Override
	public Game updateGame(int gameId, Game game) {

		Game updater = getGameById(gameId); 

		if (updater != null) {

			updater.setName(game.getName());
			updater.setMultiplayer(game.isMultiplayer());
			updater.setDescription(game.getDescription());
			updater.setImageUrl(game.getImageUrl());
			updater.setCategories(game.getCategories());
			updater.setDev(game.getDev());
			updater.setPublisher(game.getPublisher());
			updater.setPlatform(game.getPlatform());
			updater.setMods(game.getMods());
			updater.setRating(game.getRating());

			game = gameRepo.saveAndFlush(updater);

			return game;
		}

		return null;
	}

	@Override
	public boolean destroyGame(int gameId) {

		gameRepo.deleteById(gameId);
		boolean deleted = !gameRepo.existsById(gameId);
		return deleted;
	}

}
