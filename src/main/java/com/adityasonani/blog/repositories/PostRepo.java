package com.adityasonani.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adityasonani.blog.entities.Category;
import com.adityasonani.blog.entities.Post;
import com.adityasonani.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	Page<Post> findByUser(User user, Pageable page);

	Page<Post> findByCategory(Category category, Pageable page);
	
	List<Post> findByTitleContaining(String title);

}
