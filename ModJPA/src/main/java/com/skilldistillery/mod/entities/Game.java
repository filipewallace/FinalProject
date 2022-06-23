package com.skilldistillery.mod.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private boolean multiplayer;

	private String description;

	@Column(name = "img_url")
	private String imageUrl;
	
	@ManyToMany
	@JoinTable(name = "game_has_category",
	joinColumns = @JoinColumn(name = "game_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id")
			   )
	private List<Category> categories;

	public Game() {
		super();
	}
	
	public void addCategory(Category cat) {
		if (categories == null)
			categories = new ArrayList<>();
		if (!categories.contains(cat)) {
			categories.add(cat);
			cat.addGame(this);

		}
	}

	public void removeCategory(Category cat) {
		if (categories != null) {
			categories.remove(cat);
			cat.removeGame(this);
		}
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

	public boolean isMultiplayer() {
		return multiplayer;
	}

	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
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
		Game other = (Game) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", multiplayer=" + multiplayer + ", description=" + description
				+ ", imageUrl=" + imageUrl + "]";
	}

}
