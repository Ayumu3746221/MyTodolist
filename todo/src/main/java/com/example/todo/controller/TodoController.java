package com.example.todo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dio.TodoDio;
import com.example.todo.entity.Todo;
import com.example.todo.form.TodoData;
import com.example.todo.repository.TodoRepository;
import com.example.todo.todoservice.TodoService;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@CrossOrigin("http://localhost:5137/")
@AllArgsConstructor
public class TodoController {
	private TodoService todoService;
	private TodoRepository todoRepository;
	
	@GetMapping("/todo")
	public List<TodoDio> getAllTodo() {
		return todoService.getTodoList();
	}
	
	@GetMapping("/todo/{id}")
	public Optional<Todo> getMethodName(@PathVariable Integer id) {
		return todoService.getTodoFindById(id);
	}
	
	@DeleteMapping("/todo/{id}/delete")
	public String deleteTodo(@PathVariable Integer id) {
		return todoService.deleteTodo(id);
	}
	
	@PostMapping("/create/todo")
	public ResponseEntity<String> creatTodo(@Validated TodoData todoData ,BindingResult result) {
		boolean isVaild = todoService.isVaild(todoData, result);
		
		if (!result.hasErrors() && isVaild) {
			//エラーなし
			Todo todo = todoData.toEntity();
			todoRepository.saveAndFlush(todo);
			return ResponseEntity.ok("OK");
		}else {
			//エラーあり
			String message = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining("\n"));
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
}
