package com.skilldistillery.mod.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Developer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Column(name = "img_url")
	private String imageUrl;

	@Column(name = "web_link")
	private String webLink;

	public Developer() {
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getWebLink() {
		return webLink;
	}

	public void setWebLink(String webLink) {
		this.webLink = webLink;
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
		Developer other = (Developer) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Developer [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", webLink=" + webLink + "]";
	}

}
