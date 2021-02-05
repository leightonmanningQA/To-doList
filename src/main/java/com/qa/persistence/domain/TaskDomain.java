package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TaskDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	
	@ManyToOne
	private ToDoDomain myToDo;

	public TaskDomain() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskDomain(Long id, String description, ToDoDomain myToDo) {
		super();
		this.id = id;
		this.description = description;
		this.myToDo = myToDo;
	}
	

	public TaskDomain(String description, ToDoDomain myToDo) {
		super();
		this.description = description;
		this.myToDo = myToDo;
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



	public ToDoDomain getMyToDo() {
		return myToDo;
	}



	public void setMyToDo(ToDoDomain myToDo) {
		this.myToDo = myToDo;
	}



}
