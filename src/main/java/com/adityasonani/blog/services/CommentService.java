package com.adityasonani.blog.services;

import com.adityasonani.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);

	void deleteComment(Integer commentId);
}
