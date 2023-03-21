package com.adityasonani.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adityasonani.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
