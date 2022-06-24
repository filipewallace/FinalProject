package com.skilldistillery.mod.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/games")
	public List<Game> gameIndex(HttpServletRequest req, HttpServletResponse res, Principal principal) {

		// TODO PRINCIPAL
		// List<Game> games = gameServ.gameIndex();

		try {

			List<Game> games = gameServ.gameIndex();

			if (games == null) {

				res.setStatus(404);
			}

			return games;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}

	}

}
