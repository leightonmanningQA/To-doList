package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.persistence.repos.TaskRepo;

@Service
public class TaskServices {

	private TaskRepo repo;
	private ModelMapper mapper;

	@Autowired
	public TaskServices(TaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private TaskDTO mapToDTO(TaskDomain model) {
		return this.mapper.map(model, TaskDTO.class);
	}

	// POST/CREATE
	public TaskDTO create(TaskDomain task) {
		return this.mapToDTO(this.repo.save(task));
	}

	// GET/READ
	public List<TaskDTO> readAll() {
		List<TaskDomain> dbList = this.repo.findAll();
		List<TaskDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());

		return resultList;
	}

	// READ one task
	public TaskDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}

	// DELETE
	public boolean removeTask(Long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}

	// PUT/UPDATE
	public TaskDTO update(Long id, TaskDomain newDetails) {
		this.repo.findById(id);

		// task target
		newDetails.setId(id);

		return this.mapToDTO(this.repo.save(newDetails));
	}

}
