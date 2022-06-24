package com.skilldistillery.mod.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "game_mod")
public class Mod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String description;

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "date_updated")
	@UpdateTimestamp
	private LocalDateTime dateUpdated;

	private String version;

	private String requirements;

	@Column(name = "img_url")
	private String imageUrl;

	private double price;

	@Column(name = "download_link")
	private String downloadLink;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game games;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "mod")
	private List<ModMedia> modMedias;

	@OneToMany(mappedBy = "mod")
	private List<Post> posts;

	public Mod() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public Game getGames() {
		return games;
	}

	public void setGames(Game games) {
		this.games = games;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ModMedia> getModMedias() {
		return modMedias;
	}

	public void setModMedias(List<ModMedia> modMedias) {
		this.modMedias = modMedias;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addModMedia(ModMedia modMedia) {

		if (modMedias == null) {
			modMedias = new ArrayList<>();
		}

		if (!modMedias.contains(modMedia)) {
			modMedias.add(modMedia);
			modMedia.setMod(this);
		}
	}

	public void removeMedia(ModMedia modMedia) {

		modMedia.setMod(null);
		if (modMedias != null && modMedias.contains(modMedia)) {
			modMedias.remove(modMedia);
		}
	}

	public void addPost(Post post) {

		if (posts == null) {
			posts = new ArrayList<>();
		}

		if (!posts.contains(post)) {
			posts.add(post);
			post.setMod(this);
		}
	}

	public void removePost(Post post) {

		post.setMod(null);
		if (posts != null && posts.contains(post)) {
			posts.remove(post);
		}
	}

	// TODO SKILL
	// ONE TO MANY SKILL/RESUME
	// JOIN TABLE Job_skill
	// COMPOSITE ID jobSKillID
	@OneToMany(mappedBy = "mod")
	private List<Review> reviews;

	public void addReview(Review review) {

		if (reviews == null) {
			reviews = new ArrayList<>();
		}

		if (!reviews.contains(review)) {
			reviews.add(review);
			review.setMod(this);
		}

	}

	public void removeReview(Review review) {

		review.setMod(null);
		if (reviews != null && reviews.contains(review)) {
			reviews.remove(review);

		}
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
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
		Mod other = (Mod) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Mod [id=" + id + ", title=" + title + ", description=" + description + ", dateCreated=" + dateCreated
				+ ", dateUpdated=" + dateUpdated + ", version=" + version + ", requirements=" + requirements
				+ ", imageUrl=" + imageUrl + ", price=" + price + ", downloadLink=" + downloadLink + "]";
	}

}
