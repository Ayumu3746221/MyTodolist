package com.example.todo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dto.RequestedMessageDTO;
import com.example.todo.dto.TodoDTO;
import com.example.todo.dto.ErrorResponse;
import com.example.todo.entity.Todo;
import com.example.todo.form.TodoData;
import com.example.todo.repository.TodoRepository;
import com.example.todo.todoservice.TodoService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class TodoController {
	private TodoService todoService;
	private TodoRepository todoRepository;
	
	@GetMapping("/todo")
	public List<TodoDTO> getAllTodo() {
		return todoService.getTodoList();
	}
	
	@GetMapping("/todo/{id}")
	public Optional<Todo> getTodoById(@PathVariable Integer id) {
		return todoService.getTodoFindById(id);
	}
	
	@DeleteMapping("/todo/{id}/delete")
	public String deleteTodo(@PathVariable Integer id) {
		return todoService.deleteTodo(id);
	}
	
	@PutMapping("/todo/{id}/update")
	public List<RequestedMessageDTO> updateTodo(@PathVariable Integer id,@RequestBody @Validated TodoData requestUpateTodo,BindingResult result) {
		
		boolean isVaild = todoService.isVaild(requestUpateTodo, result);
		
		if ( isVaild && !result.hasErrors()) {
			//Not Error
			Todo todo = requestUpateTodo.toEntity();
			todoService.updateTodo(id, todo);
			return new RequestedMessageDTO(true,null).toMessage();
		}else {
			List<String> message = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
			return new RequestedMessageDTO(false,message).toMessage();
		}
	}
	
	@PostMapping("/create/todo")
	public ResponseEntity<?> creatTodo(@RequestBody @Validated TodoData todoData ,BindingResult result) {
		boolean isVaild = todoService.isVaild(todoData, result);
		
		if (!result.hasErrors() && isVaild) {
			//エラーなし
			Todo todo = todoData.toEntity();
			Todo createdTodo = todoRepository.saveAndFlush(todo);
			System.out.println(createdTodo);
			return ResponseEntity.ok(createdTodo);
		}else {
			List<String> errorMessages = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
			return ResponseEntity.badRequest().body(new ErrorResponse("Validation failed", errorMessages));
		}
	}
}
