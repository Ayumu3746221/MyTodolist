package com.example.todo.todoservice;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> getAllTodo(){
		return todoRepository.findAll();
	}
	
}
