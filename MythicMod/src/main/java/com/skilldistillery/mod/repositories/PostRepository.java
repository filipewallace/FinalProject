package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	Post findByTitle(String name);

	Post findByTitleAndId(String name, int gameId);

}
