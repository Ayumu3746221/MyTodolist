package com.example.todo.form;

import com.example.todo.entity.Todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TodoData {
	private Integer id;
	
	@NotBlank(message = "件名を入力してください")
	private String title;
	
	@NotEmpty(message = "達成度が入力されていません。")
	private String done;
	
	//入力データからEntityを生成して返す
	public Todo toEntity() {
		Todo todo = new Todo();
		todo.setId(id);
		todo.setTitle(title);
		todo.setDone(done);
		
		return todo;
	}
}
