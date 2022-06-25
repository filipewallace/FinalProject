package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Post;

public interface PostService {
	public Post getPostById(int postId);

//	--------------------------new------------------------------------------

	public Post showPost(String postName, int postId);

	public List<Post> postIndex();

	public Post createPost(Post post);

	public Post updatePost(int postId, Post post);

	public boolean destroyPost(int postId);

}
