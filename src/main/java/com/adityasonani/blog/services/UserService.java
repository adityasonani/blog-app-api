package com.adityasonani.blog.services;

import java.util.List;

import com.adityasonani.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);
	
	void deleteUserById(Integer userId);

	List<UserDto> getAllUsers();
}
