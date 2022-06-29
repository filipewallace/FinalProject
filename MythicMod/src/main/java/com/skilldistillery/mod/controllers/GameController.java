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
	public Game createNewGame(@RequestBody Game game,HttpServletRequest req, HttpServletResponse res, 
			Principal principal) {
System.out.println("CREATE NEW GAME CONTROLLER");
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
			System.err.println("Invalid Game JSON");
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
		
		
		System.out.println("PRINCIPAL: "+ principal);
		System.out.println("DELETING GAME: "+id);

		try {
			if (gameServ.destroyGame(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}

	}

	@GetMapping("games/{platformId}/platfrom")
	public List<Game> gamesByPlatform(@PathVariable int platformId, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {

		try {
			List<Game> games = gameServ.showGamesByPlatform(platformId);
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

	@GetMapping("games/{developerId}/developer")
	public List<Game> gamesByDeveloper(@PathVariable int developerId, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {

		try {
			List<Game> games = gameServ.showGamesByDeveloper(developerId);
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

	@GetMapping("games/{publisherId}/publisher")
	public List<Game> gamesByPublisher(@PathVariable int publisherId, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {

		try {
			List<Game> mods = gameServ.showGamesByPublisher(publisherId);
			if (mods == null) {
				res.setStatus(404);
			}
			return mods;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}

	@GetMapping("games/{ratingId}/rating")
	public List<Game> gamesByRating(@PathVariable int ratingId, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {

		try {
			List<Game> games = gameServ.showGamesByRating(ratingId);
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
	
	@GetMapping("games/{categoryId}/category")
	public List<Game> showGamesByCategory(@PathVariable int categoryId, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {

		try {
			List<Game> games = gameServ.showGamesByRating(categoryId);
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

}
