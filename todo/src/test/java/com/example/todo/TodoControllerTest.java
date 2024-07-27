package com.example.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.todo.controller.TodoController;
import com.example.todo.dto.TodoDTO;
import com.example.todo.entity.Todo;
import com.example.todo.form.TodoData;
import com.example.todo.repository.TodoRepository;
import com.example.todo.todoservice.TodoService;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoService todoService;
	
	@MockBean
	private TodoRepository todoRepository;

	@BeforeEach
	void setUp() {
		
	}
	
	@Test
	void testGetAllTodo() throws Exception {
		TodoDTO todoDTO = new TodoDTO(1, "test todo", "Y");
		when(todoService.getTodoList()).thenReturn(List.of(todoDTO));
		
		mockMvc.perform(get("/todo"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id", notNullValue()))		
		.andExpect(jsonPath("$[0].id", is(1)))		
		.andExpect(jsonPath("$[0].title",notNullValue()))
		.andExpect(jsonPath("$[0].title",is("test todo")))
		.andExpect(jsonPath("$[0].done",notNullValue()))
		.andExpect(jsonPath("$[0].done",is("Y")));		
	}
	
	@Test
	void testGetTodoById() throws Exception{
		//push TodoData
		TodoData todoData = new TodoData();
		todoData.setId(1);
		todoData.setTitle("test");
		todoData.setDitail("test detail");
		todoData.setDeadline("");
		todoData.setDone("Y");
		Todo todo = todoData.toEntity();
		when(todoService.getTodoFindById(1)).thenReturn(Optional.of(todo));
		
		mockMvc.perform(get("/todo/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", notNullValue()))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.title", notNullValue()))
		.andExpect(jsonPath("$.ditail", is("test detail")))
		.andExpect(jsonPath("$.done", notNullValue()))
		.andExpect(jsonPath("$.done",is("Y")));
	}
	
	void testCreateTodo() throws Exception{
		String responsbody = 
				"{"
				+"\"id\":1"
				+"\"title\":\"test\""
				+"\"ditail\":\"test detail\""
				+"\"deadline\":\"2025-01-01\""
				+"\"done\":\"Y\""
				+"}";
		
		mockMvc.perform(post("/create/todo")
					 .contentType(MediaType.APPLICATION_JSON)
					 .content(responsbody)
				)
				.andExpect(status().isCreated());
	}
	
	void testUpdateTodo() throws Exception{
		String responsbody = 
				"{"
				+"\"id\":1"
				+"\"title\":\"test\""
				+"\"ditail\":\"test detail\""
				+"\"deadline\":\"2025-01-01\""
				+"\"done\":\"Y\""
				+"}";
		
		mockMvc.perform(put("/todo/1/todo")
					 .contentType(MediaType.APPLICATION_JSON)
					 .content(responsbody)
				)
				.andExpect(status().isCreated());
	}
}
