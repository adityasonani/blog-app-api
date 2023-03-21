package com.adityasonani.blog.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private Integer postId;

	@NotEmpty
	@Size(min = 4, message = "title must be atleast 4 characters long!")
	private String title;

	@NotEmpty
	@Size(min = 4, max = 5000, message = "Content must be atleast 4 characters long!")
	private String content;

	private String imageName;

	private Date modifiedDate;

	private CategoryDto category;

	private UserDto user;

	private List<CommentDto> comment = new ArrayList<>();
}
