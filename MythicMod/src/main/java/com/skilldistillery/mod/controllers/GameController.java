package com.skilldistillery.mod.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.mod.entities.Game;
import com.skilldistillery.mod.services.GameService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class GameController {

	@Autowired
	private GameService gameServ;

	@GetMapping("/games/{id}")
	public Game findGameById(@PathVariable Integer id, HttpServletResponse res) {

		Game game = gameServ.getGameById(id);
		if (game == null) {

			res.setStatus(404);
		}

		return game;
	}

	@GetMapping("games")
	public List<Game> gameIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			List<Game> games = gameServ.gameIndex();
			if (games == null) {
				res.setStatus(404);
			}
			return games;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}

	@PostMapping("games")
	public Game createNewGame(HttpServletRequest req, HttpServletResponse res, @RequestBody Game game,
			Principal principal) {

		try {
			game = gameServ.createGame(game);
			if (game == null) {

				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(game.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			game = null;
		}
		return game;
	}

	@PutMapping("games/{id}")
	public Game updateGameInfo(@PathVariable int id, @RequestBody Game game, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			game = gameServ.updateGame(id, game);
			System.out.println(game);
			if (game == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			game = null;
		}
		return game;

	}

	@DeleteMapping("games/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {

		try {
			if (gameServ.destroyGame(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(400);
		}

	}

}
