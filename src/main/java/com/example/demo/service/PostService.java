package com.example.demo.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDto;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepo;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {
	@Autowired
	private AuthService authService;
	@Autowired
	private PostRepo postRepo;
	
	public void createPost(PostDto postDto) {
		Post post = new Post(); 
		
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		
		User user = authService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("Not Found User")) ;
		
		post.setUsername(user.getUsername());
		
		post.setCreatedOn(Instant.now());
		post.setUpdatedOn(null);
		
		postRepo.save(post);
	}

	public List<PostDto> showAllPosts() {
		
		List<Post> posts= postRepo.findAll();
		
		return posts.stream().map(this::mapFromPostToPostDto).collect(toList());
	
	}
	
	public PostDto mapFromPostToPostDto(Post post) {
		
		PostDto dto = new PostDto();
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());
		dto.setUsername(post.getUsername());
		
		return dto;
		
	}
	
	public PostDto readSinglePost(long id) {
		Post post = postRepo.findById(id).orElseThrow(()->new PostNotFoundException("Post Not Found id = "+ id));
		return mapFromPostToPostDto(post);
	}
}
