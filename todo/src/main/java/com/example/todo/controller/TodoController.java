package com.example.todo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.todo.entity.Todo;
import com.example.todo.todoservice.TodoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin("http://localhost:5137/")
public class TodoController {
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/home")
	public List<Todo> getAllTodo() {
		return todoService.getAllTodo();
	}
	
	
}
