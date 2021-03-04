package com.example.demo.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class Post {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column
	private long id;
	
	@NotBlank
	@Column
	private String title;
	
	@Lob
	@NotEmpty
	@Column
	private String content;
	
	@Column
	private Instant createdOn;
	
	@Column
	private Instant updatedOn;
	
	@Column
	@NotBlank
	private String username;

	public Post() {
		super();
	}

	public Post(long id, @NotBlank String title, @NotEmpty String content, Instant createdOn, Instant updatedOn,
			@NotBlank String userName) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.username = userName;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public Instant getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", createdOn=" + createdOn
				+ ", updatedOn=" + updatedOn + ", userName=" + username + "]";
	}
	
	
	
}
