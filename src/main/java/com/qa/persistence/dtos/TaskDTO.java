package com.qa.persistence.dtos;

public class TaskDTO {
	private Long id;

	private String description;

	public TaskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskDTO(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
