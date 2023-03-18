package com.adityasonani.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Integer id;

	@NotEmpty
	@Size(min = 4, message = "Username must be atleast 4 characters long!")
	private String name;

	@Email(message = "Enter valid email address!")
	private String email;

	@NotEmpty
	@Size(min = 4, message = "Password must be atleast 4 characters long!")
	private String password;

	@NotNull
	@Size(min = 4, message = "About must be atleast 4 characters long!")
	private String about;
}
