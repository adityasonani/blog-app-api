package com.adityasonani.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adityasonani.blog.entities.Comment;
import com.adityasonani.blog.entities.Post;
import com.adityasonani.blog.exceptions.ResourceNotFoundException;
import com.adityasonani.blog.payloads.CommentDto;
import com.adityasonani.blog.repositories.CommentRepo;
import com.adityasonani.blog.repositories.PostRepo;
import com.adityasonani.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment = commentRepo.save(comment);
		return this.modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		commentRepo.delete(comment);
	}

}
