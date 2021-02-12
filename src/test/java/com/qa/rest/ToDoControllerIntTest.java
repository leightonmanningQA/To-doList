package com.qa.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.qa.persistence.domain.ToDoDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.persistence.dtos.ToDoDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ToDoControllerIntTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private final int ID = 1;

	private ToDoDTO mapToDTO(ToDoDomain model) {
		return this.mapper.map(model, ToDoDTO.class);
	}

	// POST/CREATE
	@Test
	public void create() throws Exception {
		// Resources

		ToDoDomain contentBody = new ToDoDomain(null, "shopping list", null);
		ToDoDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(4L);

		// Set up request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/todo/create").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);

		// set up expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		// Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	// READ ALL todo
	@Test
	public void readAll() throws Exception {
		// Resources

		ToDoDTO expectedResult = new ToDoDTO(1L, "Shopping List",
				List.of(new TaskDTO(1L, "Tomato"), new TaskDTO(2L, "Potato")));
		ToDoDTO expectedResult2 = new ToDoDTO(2L, "House Chores", List.of(new TaskDTO(3L, "Mop Floors")));
		ToDoDTO expectedResult3 = new ToDoDTO(3L, "Bucket List", List.of());
		List<ToDoDTO> dtolist = new ArrayList<ToDoDTO>();
		dtolist.add(expectedResult);
		dtolist.add(expectedResult2);
		dtolist.add(expectedResult3);

		// Mock request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/todo/readAll");
		// Assert checks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(dtolist));
		// Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

//		//READ one todo
	@Test
	public void readToDo() throws Exception {
		// Resources
		
		ToDoDTO expectedResult = new ToDoDTO(1L, "Shopping List",
				List.of(new TaskDTO(1L, "Tomato"), new TaskDTO(2L, "Potato")));
		// Set up request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/todo/read/" + ID);

		// set up expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		// Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	// DELETE
	@Test
	public void removeToDo() throws Exception {
		// resources

		// mock request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/todo/delete/" + ID);
		// assertchecks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

//		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(OBJECT YOU WANT TO COMPARE)); CHECKS CONTENTS OF WHAT YOU GET BACK

		// perform/verfiy
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

	// PUT/UPDATE
	@Test
	public void update() throws Exception {
		// Resources
		ToDoDomain body = new ToDoDomain(2L, "NewTodoList", null);
		ToDoDTO expectedResult = this.mapToDTO(body);
		// Mock Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/todo/update/2").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(body)).accept(MediaType.APPLICATION_JSON);
		// Assert Checks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
}
