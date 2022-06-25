package com.skilldistillery.mod.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.Post;
import com.skilldistillery.mod.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Override
	public Post getPostById(int postId) {
		Optional<Post> postOpt = postRepo.findById(postId);
		Post post = null;
		if (postOpt.isPresent()) {
			post = postOpt.get();
		}
		return post;
	}

	@Override
	public Post showPost(String postName, int postId) {
		return postRepo.findByTitleAndId(postName, postId);
	}

	@Override
	public List<Post> postIndex() {
		return postRepo.findAll();
	}

	@Override
	public Post createPost(Post post) {
		return postRepo.saveAndFlush(post);
	}

	@Override
	public Post updatePost(int postId, Post post) {
		Post updater = getPostById(postId);
		
		if(updater != null) {
			updater.setComment(post.getComment());
			updater.setCommentDate(post.getCommentDate());
			updater.setInReplyTo(post.getInReplyTo());
			updater.setMod(post.getMod());
			updater.setReplies(post.getReplies());
			updater.setTitle(post.getTitle());
			updater.setUser(post.getUser());
			
			post = postRepo.saveAndFlush(updater);
			
			return post;
		}
		
		return null;
	}

	@Override
	public boolean destroyPost(int postId) {
		postRepo.deleteById(postId);
		boolean deleted = !postRepo.existsById(postId);
		return deleted;
	}

	

}
