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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@JoinTable(name = "game_has_category", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	@ManyToOne
	@JoinColumn(name = "developer_id")
	private Developer dev;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;

	@ManyToOne
	@JoinColumn(name = "platform_id")
	private Platform platform;
	
	
	@ManyToOne
	@JoinColumn(name = "esrb_rating_id")
	private EsrbRating rating;
	
	@JsonIgnoreProperties({"game"})
	@OneToMany(mappedBy = "game")
	private List<Mod> mods;

	public Game() {
		super();
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
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

	public Developer getDev() {
		return dev;
	}

	public void setDev(Developer dev) {
		this.dev = dev;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public EsrbRating getRating() {
		return rating;
	}

	public void setRating(EsrbRating rating) {
		this.rating = rating;
	}

	public List<Mod> getMods() {
		return mods;
	}

	public void setMods(List<Mod> mods) {
		this.mods = mods;
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

	public void addMod(Mod mod) {

		if (mods == null) {
			mods = new ArrayList<>();
		}

		if (!mods.contains(mod)) {
			mods.add(mod);
			mod.setGame(this);
		}
	}

	public void removeMod(Mod mod) {

		mod.setGame(null);
		if (mods != null && mods.contains(mod)) {
			mods.remove(mod);
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
		Game other = (Game) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", multiplayer=" + multiplayer + ", description=" + description
				+ ", imageUrl=" + imageUrl + "]";
	}

	
	
}
