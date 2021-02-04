package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.services.TaskServices;

@RestController
@RequestMapping("/task")
public class TaskController {

	private Long id = 0L;
	private TaskServices service;
//
	@Autowired
	public TaskController(TaskServices service) {
		super();
		this.service = service;
	}

// POST/CREATE
	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDomain task) {
		return new ResponseEntity<TaskDTO>(this.service.create(task), HttpStatus.CREATED);
	}

// READ ALL tasks
	@GetMapping("/readAll")
	public ResponseEntity<List<TaskDTO>> readAll() {
		return new ResponseEntity<List<TaskDTO>>(this.service.readAll(), HttpStatus.OK);
	}
//	//READ one TASK
	@GetMapping("/read/{id}")
	public ResponseEntity<TaskDTO> readTask(@PathVariable("id")Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}
	// PUT/UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDTO> update(@PathVariable("id")Long id, @RequestBody TaskDomain task) {
		return new ResponseEntity<TaskDTO>(this.service.update(id, task),HttpStatus.ACCEPTED);
	}
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeTask(@PathVariable Long id) {

		return this.service.removeTask(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}