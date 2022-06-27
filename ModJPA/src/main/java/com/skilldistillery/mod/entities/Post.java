package com.skilldistillery.mod.entities;

import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String comment;

	@Column(name = "comment_date")
	@CreationTimestamp
	private LocalDateTime commentDate;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	

	@ManyToOne
	@JoinColumn(name = "mod_id")
	private Mod mod;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "reply_to_id")
	private Post inReplyTo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "inReplyTo")
	private List<Post> replies;
	

	public Post() {
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Mod getMod() {
		return mod;
	}

	public void setMod(Mod mod) {
		this.mod = mod;
	}

	public Post getInReplyTo() {
		return inReplyTo;
	}

	public void setInReplyTo(Post inReplyTo) {
		this.inReplyTo = inReplyTo;
	}

	public List<Post> getReplies() {
		return replies;
	}

	public void setReplies(List<Post> replies) {
		this.replies = replies;
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
		Post other = (Post) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", comment=" + comment + ", commentDate=" + commentDate + "]";
	}

}
