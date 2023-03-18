package com.adityasonani.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adityasonani.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
