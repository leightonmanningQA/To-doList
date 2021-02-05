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

import com.qa.persistence.domain.ToDoDomain;
import com.qa.persistence.dtos.ToDoDTO;
import com.qa.services.ToDoServices;


@RestController
@RequestMapping("/todo")
public class ToDoController {

	private ToDoServices service;

	@Autowired
	public ToDoController(ToDoServices service) {
		super();
		this.service = service;
	}

	// POST/CREATE
	@PostMapping("/create")
	public ResponseEntity<ToDoDTO> create(@RequestBody ToDoDomain todo) {
		return new ResponseEntity<ToDoDTO>(this.service.create(todo), HttpStatus.CREATED);
	}

	// READ ALL todo
	@GetMapping("/readAll")
	public ResponseEntity<List<ToDoDTO>> readAll() {
		return new ResponseEntity<List<ToDoDTO>>(this.service.readAll(), HttpStatus.OK);
	}
	
//	//READ one todo
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoDTO> readToDo(@PathVariable("id")Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeToDo(@PathVariable Long id) {

		return this.service.removeToDo(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}
	// PUT/UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoDTO> update(@PathVariable("id")Long id, @RequestBody ToDoDomain todo) {
		return new ResponseEntity<ToDoDTO>(this.service.update(id, todo),HttpStatus.ACCEPTED);
	}

}
