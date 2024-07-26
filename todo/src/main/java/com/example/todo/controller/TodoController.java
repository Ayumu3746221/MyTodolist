package com.example.todo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.todo.dio.TodoDio;
import com.example.todo.entity.Todo;
import com.example.todo.form.RequestedMessage;
import com.example.todo.form.TodoData;
import com.example.todo.repository.TodoRepository;
import com.example.todo.todoservice.TodoService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	@PutMapping("/todo/{id}/update")
	public List<RequestedMessage> updateTodo(@PathVariable Integer id,@RequestBody @Validated TodoData requestUpateTodo,BindingResult result) {
		
		boolean isVaild = todoService.isVaild(requestUpateTodo, result);
		
		if ( isVaild && !result.hasErrors()) {
			//Not Error
			Todo todo = requestUpateTodo.toEntity();
			todoService.updateTodo(id, todo);
			return new RequestedMessage(true,null).toMessage();
		}else {
			List<String> message = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
			return new RequestedMessage(false,message).toMessage();
		}
	}
	
	@PostMapping("/create/todo")
	public List<RequestedMessage> creatTodo(@RequestBody @Validated TodoData todoData ,BindingResult result) {
		boolean isVaild = todoService.isVaild(todoData, result);
		
		if (!result.hasErrors() && isVaild) {
			//エラーなし
			Todo todo = todoData.toEntity();
			todoRepository.saveAndFlush(todo);
			return new RequestedMessage(true,null).toMessage();
		}else {
			//エラーあり
			List<String> message = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
			return new RequestedMessage(false,message).toMessage();
		}
	}
}
