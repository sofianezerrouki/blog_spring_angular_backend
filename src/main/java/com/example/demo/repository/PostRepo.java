package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.PostDto;
import com.example.demo.model.Post;

public interface PostRepo extends JpaRepository<Post, Long> {

	
	
}
