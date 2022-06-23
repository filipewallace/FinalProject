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
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String username;

	private String password;

	private String email;

	private String role;

	private boolean enabled;

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "img_url")
	private String image;
	
	
	@OneToMany(mappedBy = "user")
	private List<Mod> mods;
	
	
	@OneToMany(mappedBy = "user")
	private List<ModMedia> modMedias;
	
	

	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Mod> getMods() {
		return mods;
	}

	public void setMods(List<Mod> mods) {
		this.mods = mods;
	}
	
	public void addMod(Mod mod) {

		if (mods == null) {
			mods = new ArrayList<>();
		}

		if (!mods.contains(mod)) {
			mods.add(mod);
			mod.setUser(this);
		}
	}

	public void removeMod(Mod mod) {

		mod.setUser(null);
		if (mods != null && mods.contains(mod)) {
			mods.remove(mod);
		}
	}
	
	
	public void addModMedias(ModMedia modMedia) {

		if (modMedias == null) {
			modMedias = new ArrayList<>();
		}

		if (!modMedias.contains(modMedia)) {
			modMedias.add(modMedia);
			modMedia.setUser(this);
		}
	}

	public void removeModMedias(ModMedia modMedia) {

		modMedia.setUser(null);
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
			post.setUser(this);
		}
	}

	public void removePost(Post post) {

		post.setUser(null);
		if (posts != null && posts.contains(post)) {
			posts.remove(post);
		}
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}

}
