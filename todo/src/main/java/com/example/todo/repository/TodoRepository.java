package com.example.todo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.todo.dto.TodoDTO;
import com.example.todo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer>{	
	@Query(value = "SELECT  new com.example.todo.dto.TodoDTO(todolist.id , todolist.title, todolist.done) FROM Todo todolist")
	List<TodoDTO> findIdAndTitleAndDoneList();
}
