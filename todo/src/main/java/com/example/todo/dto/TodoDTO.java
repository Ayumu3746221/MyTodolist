package com.example.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoDTO {
	private Integer id;
	private String title;
	private String done;
	
	public TodoDTO(Integer id,String title,String done) {
		this.id = id;
		this.title = title;
		this.done = done;
	}
}
