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
import com.qa.persistence.domain.ToDoDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.persistence.dtos.ToDoDTO;
import com.qa.persistence.repos.ToDoRepo;

@SpringBootTest
public class ToDoServicesUnitTest {
	
	@MockBean
	private ModelMapper mapper;

	@MockBean
	private ToDoRepo mockrepo;

	@Autowired
	private ToDoServices service;

	private ToDoDTO mapToDTO(ToDoDomain model) {
		return this.mapper.map(model, ToDoDTO.class);
	}
	@Test
	public void create() {
		// RESOURCE
		ToDoDomain TEST_TODO = new ToDoDomain(1L, "chores",null);
		ToDoDTO TEST_DTO = new ToDoDTO(1L,"chores",null);

		// RULES
		Mockito.when(this.mockrepo.save(Mockito.any(ToDoDomain.class))).thenReturn(TEST_TODO);
		Mockito.when(this.mapper.map(TEST_TODO,ToDoDTO.class)).thenReturn(TEST_DTO);

		// ACTIONS
		ToDoDTO result = this.service.create(TEST_TODO);

		// ASSERTIONS
		Assertions.assertThat(result).isNotNull(); //CHECKS WHETHER WE ARE HANDLING NOT NULL OBJECT.
		Assertions.assertThat(result).isEqualTo(TEST_DTO);

		
		Mockito.verify(this.mockrepo, Mockito.times(1)).save(Mockito.any(ToDoDomain.class));
		Mockito.verify(this.mapper, Mockito.times(1)).map(TEST_TODO, ToDoDTO.class);
	}
	@Test
	public void readOne() {
		// RESOURCE

		ToDoDomain TEST_TODO = new ToDoDomain(1L, "chores",null);
		ToDoDTO TEST_DTO = this.mapper.map(TEST_TODO, ToDoDTO.class);

		// RULES

		Mockito.when(this.mockrepo.findById(TEST_TODO.getId())).thenReturn(Optional.of(TEST_TODO));

		// ACTIONS

		ToDoDTO result = this.service.readOne(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(TEST_DTO);
		
		Mockito.verify(this.mockrepo, Mockito.times(1)).findById(1L);
	}
	@Test
	public void removeToDo() {
		Long toDoId = 1L;
		boolean exists=false;
		
		
		Mockito.when(mockrepo.existsById(toDoId)).thenReturn(false);
		
		Assertions.assertThat(this.service.removeToDo(toDoId)).isTrue();
		
		Mockito.verify(this.mockrepo, Mockito.times(1)).existsById(toDoId);
	
		
		//Mockito.when(this.mockrepo.deleteById(TEST_CAT.getId())).thenReturn(false);
		
		
	}
	@Test
	public void update() {
		// RESOURCES
		ToDoDomain TEST_TODO = new ToDoDomain(1L, "Chore List", null);
		ToDoDomain UPDATEDTODO = new ToDoDomain(1L, "Shopping", null);
		ToDoDTO EXPECTED = new ToDoDTO(1L, "Chore List");

		// Rules
		Mockito.when(this.mockrepo.findById(1L)).thenReturn(Optional.of(TEST_TODO));
		Mockito.when(this.mockrepo.save(Mockito.any(ToDoDomain.class))).thenReturn(UPDATEDTODO);
		Mockito.when(this.mapper.map(UPDATEDTODO, ToDoDTO.class)).thenReturn(EXPECTED);

		// Actions
		ToDoDTO RESULT = this.service.update(1L, UPDATEDTODO);

		// Assertions
		Assertions.assertThat(RESULT).isNotNull();
		Assertions.assertThat(RESULT).isEqualTo(EXPECTED);
		Assertions.assertThat(RESULT).usingRecursiveComparison().isEqualTo(EXPECTED);
		Mockito.verify(this.mockrepo, Mockito.times(1)).save(Mockito.any(ToDoDomain.class));
		Mockito.verify(this.mapper, Mockito.times(1)).map(UPDATEDTODO, ToDoDTO.class);

	}
	@Test
	public void readAll() {
		//RESOURCES
		ToDoDomain TEST_TODO = new ToDoDomain(1L,"Mopping", null);
		ToDoDomain TEST_TODO2 = new ToDoDomain(2L,"Hoovering", null);
		ToDoDTO TEST_DTO = new ToDoDTO(1L,"Mopping");
		ToDoDTO TEST_DTO2 = new ToDoDTO(2L,"Hoovering");
		
		List<ToDoDomain> TODO_LIST = new ArrayList<>();
		List<ToDoDTO> DTO_LIST = new ArrayList<>();
		TODO_LIST.add(TEST_TODO);
		TODO_LIST.add(TEST_TODO2);
		DTO_LIST.add(TEST_DTO);
		DTO_LIST.add(TEST_DTO2);
		
		//RULES
		Mockito.when(this.mockrepo.findAll()).thenReturn(TODO_LIST);
		Mockito.when(this.mapper.map(TEST_TODO, ToDoDTO.class)).thenReturn(TEST_DTO);
		Mockito.when(this.mapper.map(TEST_TODO2, ToDoDTO.class)).thenReturn(TEST_DTO2);
		
		//Actions
		List<ToDoDTO> result = this.service.readAll();
		
		//ASSERTIONS
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(DTO_LIST);
		Assertions.assertThat(result).isEqualTo(DTO_LIST);
		Mockito.verify(this.mapper, Mockito.times(1)).map(TEST_TODO, ToDoDTO.class);
		Mockito.verify(this.mapper, Mockito.times(1)).map(TEST_TODO2, ToDoDTO.class);
		Mockito.verify(this.mockrepo, Mockito.times(1)).findAll();
	}

}
