package com.adityasonani.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	private Integer id;

	@NotEmpty
	@Size(min = 3, max = 50, message = "Title must be atleast 3 and atmax 50 chars long!")
	private String title;

	@NotEmpty
	@Size(min = 3, message = "Description must be atleast 3 chars long!")
	private String description;
}
