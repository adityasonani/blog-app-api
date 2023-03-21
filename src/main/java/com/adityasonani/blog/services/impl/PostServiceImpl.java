package com.adityasonani.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adityasonani.blog.entities.Category;
import com.adityasonani.blog.entities.Post;
import com.adityasonani.blog.entities.User;
import com.adityasonani.blog.exceptions.ResourceNotFoundException;
import com.adityasonani.blog.payloads.PostDto;
import com.adityasonani.blog.payloads.PostResponse;
import com.adityasonani.blog.repositories.CategoryRepo;
import com.adityasonani.blog.repositories.PostRepo;
import com.adityasonani.blog.repositories.UserRepo;
import com.adityasonani.blog.services.PostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		Category category = categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", catId));

		Post post = dtoToPost(postDto);
		post.setImageName("default.png");
		post.setModifiedDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		postRepo.save(post);

		return postToDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = postRepo.save(post);
		return postToDto(updatedPost);
	}

	@Override
	public void deletePostById(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagedRecords = postRepo.findAll(page);
		List<PostDto> allPosts = pagedRecords.getContent().stream().map(pos -> postToDto(pos))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse(allPosts, pagedRecords.getNumber(), pagedRecords.getSize(),
				pagedRecords.getTotalElements(), pagedRecords.getTotalPages(), pagedRecords.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		return postToDto(post);
	}

	@Override
	public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		Page<Post> pagedRecords = postRepo.findByCategory(category, page);
		List<PostDto> allPosts = pagedRecords.getContent().stream().map(pos -> postToDto(pos))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse(allPosts, pagedRecords.getNumber(), pagedRecords.getSize(),
				pagedRecords.getTotalElements(), pagedRecords.getTotalPages(), pagedRecords.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagedRecords = postRepo.findByUser(user, page);
		List<PostDto> allPosts = pagedRecords.stream().map(pos -> postToDto(pos)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse(allPosts, pagedRecords.getNumber(), pagedRecords.getSize(),
				pagedRecords.getTotalElements(), pagedRecords.getTotalPages(), pagedRecords.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> searchPostsByTitle(String title) {
		List<Post> foundPosts = postRepo.findByTitleContaining(title);
		List<PostDto> allPosts = foundPosts.stream().map(pos -> postToDto(pos))
				.collect(Collectors.toList());
		return allPosts;
	}

	private Post dtoToPost(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		return post;
	}

	private PostDto postToDto(Post post) {
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
	}

}
