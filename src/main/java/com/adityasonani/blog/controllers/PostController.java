package com.adityasonani.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adityasonani.blog.config.AppConstants;
import com.adityasonani.blog.payloads.ApiResponse;
import com.adityasonani.blog.payloads.PostDto;
import com.adityasonani.blog.payloads.PostResponse;
import com.adityasonani.blog.services.PostService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/cat/{catId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("catId") Integer catId) {
		PostDto createdPost = postService.createPost(postDto, userId, catId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable("userId") Integer userId,
			@RequestParam(name = "page", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer page,
			@RequestParam(name = "size", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer size) {
		PostResponse postsByUser = postService.getPostsByUser(userId, page, size);
		return new ResponseEntity<PostResponse>(postsByUser, HttpStatus.OK);
	}

	@GetMapping("/cat/{catId}/posts")
	public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable("catId") Integer catId,
			@RequestParam(name = "page", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer page,
			@RequestParam(name = "size", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer size) {
		PostResponse postsByCategory = postService.getPostsByCategory(catId, page, size);
		return new ResponseEntity<PostResponse>(postsByCategory, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name = "page", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer page,
			@RequestParam(name = "size", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer size) {
		PostResponse posts = postService.getAllPosts(page, size);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId) {
		PostDto posts = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(posts, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostsById(@PathVariable Integer postId) {
		postService.deletePostById(postId);
		return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostsByKeyword(@PathVariable("keyword") String keyword) {
		List<PostDto> posts = postService.searchPostsByTitle(keyword);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

}
