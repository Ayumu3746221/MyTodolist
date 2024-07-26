package com.example.todo.dio;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoDio {
	private Integer id;
	private String title;
	private String done;
	
	public TodoDio(Integer id,String title,String done) {
		this.id = id;
		this.title = title;
		this.done = done;
	}
}
