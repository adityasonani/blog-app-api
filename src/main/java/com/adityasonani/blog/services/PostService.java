package com.adityasonani.blog.services;

import java.util.List;

import com.adityasonani.blog.entities.Post;
import com.adityasonani.blog.payloads.PostDto;
import com.adityasonani.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer catId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePostById(Integer postId);

	PostResponse getAllPosts(Integer pageNumber, Integer pageSize);

	PostDto getPostById(Integer postId);

	PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

	PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize);

	List<PostDto> searchPostsByTitle(String title);

}
