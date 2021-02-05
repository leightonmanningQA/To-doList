package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qa.persistence.domain.ToDoDomain;
import com.qa.persistence.dtos.ToDoDTO;
import com.qa.persistence.repos.ToDoRepo;

@Service
public class ToDoServices {

	private ToDoRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public ToDoServices(ToDoRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private ToDoDTO mapToDTO(ToDoDomain model) {
		return this.mapper.map(model,ToDoDTO.class);
	}
	// POST/CREATE
	public ToDoDTO create(ToDoDomain todo) {
		return this.mapToDTO(this.repo.save(todo));
	}
	
	//GET/READ
	public List<ToDoDTO> readAll() {
		List<ToDoDomain> dbList = this.repo.findAll();
		List<ToDoDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		
		return resultList;
	}

	//READ one todo 
	public ToDoDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	// DELETE
	public boolean removeToDo(Long id) {
		//remove todo and return it
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
	
	// PUT/UPDATE
	public ToDoDTO update(Long id, ToDoDomain newDetails) {
	  this.repo.findById(id).orElseThrow();
	  
	  //todo target
	  newDetails.setId(id);
	  
	  return this.mapToDTO(this.repo.save(newDetails));
	}
}
