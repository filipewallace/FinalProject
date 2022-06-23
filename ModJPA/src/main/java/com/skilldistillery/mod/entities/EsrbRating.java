package com.skilldistillery.mod.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "esrb_rating")
public class EsrbRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "rating")
	private List<Game> games;

	public EsrbRating() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public void addGame(Game game) {

		if (games == null) {
			games = new ArrayList<>();
		}

		if (!games.contains(game)) {
			games.add(game);
			game.setRating(this);
		}
	}

	public void removeGame(Game game) {

		game.setRating(null);
		if (games != null && games.contains(game)) {
			games.remove(game);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EsrbRating other = (EsrbRating) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "EsrbRating [id=" + id + ", name=" + name + "]";
	}

}
