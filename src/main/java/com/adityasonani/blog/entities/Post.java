package com.adityasonani.blog.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(length = 5000, nullable = false)
	private String content;

	private String imageName;

	private Date modifiedDate;

	@ManyToOne
	private Category category;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comment = new ArrayList<>();

}
