package com.qa.services;

import java.util.ArrayList;
import java.util.List;
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
		TaskDomain TEST_TASK = new TaskDomain(1L, "washingup", null);
		TaskDTO TEST_DTO = new TaskDTO(1L, "washingup");

		// RULES
		Mockito.when(this.mockrepo.save(Mockito.any(TaskDomain.class))).thenReturn(TEST_TASK);
		Mockito.when(this.mapper.map(TEST_TASK, TaskDTO.class)).thenReturn(TEST_DTO);

		// ACTIONS
		TaskDTO result = this.service.create(TEST_TASK);

		// ASSERTIONS
		Assertions.assertThat(result).isNotNull(); // CHECKS WHETHER WE ARE HANDLING NOT NULL OBJECT.
		Assertions.assertThat(result).isEqualTo(TEST_DTO);

		Mockito.verify(this.mockrepo, Mockito.times(1)).save(Mockito.any(TaskDomain.class));
		Mockito.verify(this.mapper, Mockito.times(1)).map(TEST_TASK, TaskDTO.class);
	}

	@Test
	public void readOne() {
		// RESOURCE

		TaskDomain TEST_TASK = new TaskDomain(1L, "washingup", null);
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
		// RESOURCES
		Long taskId = 1L;

		// RULES
		Mockito.when(mockrepo.existsById(taskId)).thenReturn(false);

		// ACTIONS
		boolean RESULT = this.service.removeTask(taskId);

		// ASSERTIONS
		Assertions.assertThat(RESULT).isNotNull();
		Assertions.assertThat(RESULT).isEqualTo(true);
		Mockito.verify(this.mockrepo, Mockito.times(1)).existsById(taskId);
		Mockito.verify(this.mockrepo, Mockito.times(1)).deleteById(taskId);

	}

	@Test
	public void update() {
		// RESOURCES
		TaskDomain TEST_TASK = new TaskDomain(1L, "Mopping", null);
		TaskDomain UPDATEDTEST_TASK = new TaskDomain(1L, "Hoovering", null);
		TaskDTO EXPECTED = new TaskDTO(1L, "Hoovering");

		// Rules
		Mockito.when(this.mockrepo.findById(1L)).thenReturn(Optional.of(TEST_TASK));
		Mockito.when(this.mockrepo.save(Mockito.any(TaskDomain.class))).thenReturn(UPDATEDTEST_TASK);
		Mockito.when(this.mapper.map(UPDATEDTEST_TASK, TaskDTO.class)).thenReturn(EXPECTED);

		// Actions
		TaskDTO RESULT = this.service.update(1L, UPDATEDTEST_TASK);

		// Assertions
		Assertions.assertThat(RESULT).isNotNull();
		Assertions.assertThat(RESULT).isEqualTo(EXPECTED);
		Assertions.assertThat(RESULT).usingRecursiveComparison().isEqualTo(EXPECTED);
		Mockito.verify(this.mockrepo, Mockito.times(1)).save(Mockito.any(TaskDomain.class));
		Mockito.verify(this.mapper, Mockito.times(1)).map(UPDATEDTEST_TASK, TaskDTO.class);

	}
	@Test
	public void readAll() {
		//RESOURCES
		TaskDomain TEST_TASK = new TaskDomain(1L,"Mopping", null);
		TaskDomain TEST_TASK2 = new TaskDomain(2L,"Hoovering", null);
		TaskDTO TEST_DTO = new TaskDTO(1L,"Mopping");
		TaskDTO TEST_DTO2 = new TaskDTO(2L,"Hoovering");
		
		List<TaskDomain> TASK_LIST = new ArrayList<>();
		List<TaskDTO> DTO_LIST = new ArrayList<>();
		TASK_LIST.add(TEST_TASK);
		TASK_LIST.add(TEST_TASK2);
		DTO_LIST.add(TEST_DTO);
		DTO_LIST.add(TEST_DTO2);
		
		//RULES
		Mockito.when(this.mockrepo.findAll()).thenReturn(TASK_LIST);
		Mockito.when(this.mapper.map(TEST_TASK, TaskDTO.class)).thenReturn(TEST_DTO);
		Mockito.when(this.mapper.map(TEST_TASK2, TaskDTO.class)).thenReturn(TEST_DTO2);
		
		//Actions
		List<TaskDTO> result = this.service.readAll();
		
		//ASSERTIONS
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(DTO_LIST);
		Assertions.assertThat(result).isEqualTo(DTO_LIST);
		Mockito.verify(this.mapper, Mockito.times(1)).map(TEST_TASK, TaskDTO.class);
		Mockito.verify(this.mapper, Mockito.times(1)).map(TEST_TASK2, TaskDTO.class);
		Mockito.verify(this.mockrepo, Mockito.times(1)).findAll();
	}

}
