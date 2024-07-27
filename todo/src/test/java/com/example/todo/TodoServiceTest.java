package com.example.todo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.example.todo.entity.Todo;
import com.example.todo.form.TodoData;
import com.example.todo.repository.TodoRepository;
import com.example.todo.todoservice.TodoService;

class TodoServiceTest {

	// set testing class
	private TodoService service;
	private TodoRepository todoRepository;

	@BeforeEach
	void setUp() {
		service = new TodoService(todoRepository);
	}

	@Test
	void shouldReturnTrueIsValid() {
		TodoData todoData = new TodoData();

		todoData.setId(1);
		todoData.setTitle("test");
		todoData.setDitail("test");
		todoData.setDeadline("2024-03-07");
		todoData.setDone("N");
		
		Todo todo = todoData.toEntity();

		BindingResult result = new BeanPropertyBindingResult(todo, "todo");
		boolean isVaild = service.isVaild(todoData, result);

		assertTrue(isVaild);
	}

	@Test
	void shouldReturnFalseWhenDeadlineIsValid() {
		TodoData todoData = new TodoData();

		todoData.setId(1);
		todoData.setTitle("test");
		todoData.setDitail("test");
		todoData.setDeadline("2000001001");
		todoData.setDone("Y");

		Todo todo = todoData.toEntity();
		
		BindingResult result = new BeanPropertyBindingResult(todo, "todo");
		boolean isVaild = service.isVaild(todoData, result);

		assertFalse(isVaild);
		assertEquals(1,result.getErrorCount(),"yyyy-MM-dd形式で入力してください");
	}

	@Test
	void shouldReturnTrueWhenDeadlineIsNull() {
		TodoData todoData = new TodoData();

		todoData.setId(1);
		todoData.setTitle("test");
		todoData.setDitail("test");
		todoData.setDeadline(null);
		todoData.setDone("N");

		BindingResult result = new BeanPropertyBindingResult(todoData, "todoData");
		boolean isVaild = service.isVaild(todoData, result);

		assertTrue(isVaild);
	}
	
}
