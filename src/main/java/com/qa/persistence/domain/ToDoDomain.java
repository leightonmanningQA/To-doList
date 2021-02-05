package com.qa.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
public class ToDoDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@OneToMany(mappedBy = "myToDo", fetch=FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<TaskDomain> taskList;
	
	public ToDoDomain() {
		super();
		
	}

	public ToDoDomain(Long id, String title, List<TaskDomain> taskList) {
		super();
		this.id = id;
		this.title = title;
		this.taskList = taskList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TaskDomain> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskDomain> taskList) {
		this.taskList = taskList;
	}
	

	
	
}


