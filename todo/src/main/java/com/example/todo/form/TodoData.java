package com.example.todo.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.todo.entity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class  TodoData {
	
	private Integer id;
	
	@NotNull(message = "件名を入力してください")
	@NotBlank(message = "件名を入力してください")
	private String title;
	
	private String ditail;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String deadline;
	
	@NotEmpty(message = "達成度が入力されていません")
	private String done;
	
	//入力データからEntityを生成して返す
	public Todo toEntity() {
		Todo todo = new Todo();
		todo.setId(id);
		todo.setTitle(title);
		todo.setDitail(ditail);
		todo.setDone(done);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long ms = format.parse(deadline).getTime();
			todo.setDeadline(new Date(ms));
		} catch (ParseException e) {
			todo.setDeadline(null);
		}
		return todo;
	}
}
