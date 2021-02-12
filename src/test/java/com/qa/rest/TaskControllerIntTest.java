package com.qa.rest;

import java.util.List;

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
import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TaskControllerIntTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private final int ID = 1;

	private TaskDTO mapToDTO(TaskDomain model) {
		return this.mapper.map(model, TaskDTO.class);
	}

	// POST/CREATE
	@Test
	public void create() throws Exception {
		// Resources

		TaskDomain contentBody = new TaskDomain("washing up", null);
		TaskDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(4L);

		// Set up request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/task/create").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);

		// set up expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		// Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

//		//READ one TASK
	@Test
	public void readTask() throws Exception {
		// Resources
		TaskDTO expectedResult = new TaskDTO(1L, "Tomato");
		// Set up request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/task/read/" + ID);

		// set up expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

		// Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	// DELETE
	@Test
	public void removeTask() throws Exception {
		// resources

		// mock request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/task/delete/" + ID);
		// assertchecks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

		// perform/verfiy
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

	// PUT/UPDATE
	@Test
	public void update() throws Exception {
		//Resources
		TaskDomain body = new TaskDomain(2L,"Hoovering", null);
		TaskDTO expectedResult = this.mapToDTO(body);
		// Mock Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/task/update/2").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(body)).accept(MediaType.APPLICATION_JSON);
		//Assert Checks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		//Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		

	}
	// READ ALL TASKS
	@Test
	public void readAll() throws Exception {
		//Resources
		List<TaskDTO> result = List.of(
				new TaskDTO(1L, "Tomato"),
				new TaskDTO(2L, "Potato"),
				new TaskDTO(3L, "Mop Floors"));
		// Mock request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/task/readAll/");
		// Assert checks
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(result));
		//Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		
	}
}
