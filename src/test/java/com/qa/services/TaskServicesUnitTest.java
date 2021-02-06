package com.qa.services;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.persistence.repos.TaskRepo;

@SpringBootTest
public class TaskServicesUnitTest {
	
	@MockBean
	private ModelMapper mapper;

	@MockBean
	private TaskRepo mockrepo;

	@Autowired
	private TaskServices service;

	private TaskDTO mapToDTO(TaskDomain model) {
		return this.mapper.map(model, TaskDTO.class);
	}
	@Test
	public void create() {
		// RESOURCE
		TaskDomain TEST_TASK = new TaskDomain(1L, "washingup",null);
		TaskDTO TEST_DTO = new TaskDTO(1L,"washingup");

		// RULES
		Mockito.when(this.mockrepo.save(Mockito.any(TaskDomain.class))).thenReturn(TEST_TASK);
		Mockito.when(this.mapper.map(TEST_TASK,TaskDTO.class)).thenReturn(TEST_DTO);

		// ACTIONS
		TaskDTO result = this.service.create(TEST_TASK);

		// ASSERTIONS
		Assertions.assertThat(result).isNotNull(); //CHECKS WHETHER WE ARE HANDLING NOT NULL OBJECT.
		Assertions.assertThat(result).isEqualTo(TEST_DTO);

		
		Mockito.verify(this.mockrepo, Mockito.times(1)).save(Mockito.any(TaskDomain.class));
		Mockito.verify(this.mapper, Mockito.times(1)).map(TEST_TASK, TaskDTO.class);
	}
	@Test
	public void readOne() {
		// RESOURCE

		TaskDomain TEST_TASK = new TaskDomain(1L, "washingup",null);
		TaskDTO TEST_DTO = this.mapper.map(TEST_TASK, TaskDTO.class);

		// RULES

		Mockito.when(this.mockrepo.findById(TEST_TASK.getId())).thenReturn(Optional.of(TEST_TASK));

		// ACTIONS

		TaskDTO result = this.service.readOne(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(TEST_DTO);
		
		Mockito.verify(this.mockrepo, Mockito.times(1)).findById(1L);
	}
	@Test
	public void removeTask() {
		Long taskId = 1L;
		boolean exists=false;
		
		
		Mockito.when(mockrepo.existsById(taskId)).thenReturn(false);
		
		Assertions.assertThat(this.service.removeTask(taskId)).isTrue();
		
		Mockito.verify(this.mockrepo, Mockito.times(1)).existsById(taskId);
	
		
		//Mockito.when(this.mockrepo.deleteById(TEST_CAT.getId())).thenReturn(false);
		
		
	}
//	@Test
//	public void update() {
//		
//	}
//	@Test
//	public void readAll() {
//		
//	}

}
