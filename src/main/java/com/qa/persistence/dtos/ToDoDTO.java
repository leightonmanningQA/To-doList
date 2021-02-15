package com.qa.persistence.dtos;



import java.util.List;

public class ToDoDTO {

	private Long id;
	private String title;
	private List<TaskDTO> taskList;
	




	public ToDoDTO(Long id, String title, List<TaskDTO> taskList) {
		super();
		this.id = id;
		this.title = title;
		this.taskList = taskList;
	}

	public ToDoDTO() {
		super();
		
	}

	public ToDoDTO(long l, String string) {
		
	}

	public ToDoDTO(String string) {
		
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

	public List<TaskDTO> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskDTO> taskList) {
		this.taskList = taskList;
	}
	


}
