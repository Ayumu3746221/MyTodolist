package com.example.todo.todoservice;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.todo.dio.TodoDio;
import com.example.todo.entity.Todo;
import com.example.todo.form.TodoData;
import com.example.todo.repository.TodoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoService {
	private TodoRepository todoRepository;
	
	//全てのデータを返すメソッド（テスト用）
	public List<Todo> getAllTodo(){
		return todoRepository.findAll();
	}
	
	//データのId,title,Doneを取得するメソッド
	public List<TodoDio> getTodoList(){
		return todoRepository.findIdAndTitleAndDoneList();
	}
	
	//データをidから検索するメソッド
	public Optional<Todo> getTodoFindById(Integer id){
		return todoRepository.findById(id);
	}
	
	//バリテーションチェック
	public boolean isVaild(TodoData todoData,BindingResult result) {
		boolean ans = true;
		
		//データフォーマットについて
		String deadline = todoData.getDeadline();
		if (!deadline.equals("")) {
			try {
				LocalDate.parse(deadline);
			} catch (DateTimeException e) {
				FieldError fieldError = new FieldError(result.getObjectName(), "deadline", "yyyy-mm-dd形式で入力してください");
				result.addError(fieldError);
				ans = false;
			}
		}
		
		
		return ans;
	}
	
	//Todoを削除するメソッド
	public String deleteTodo(Integer id) {
		String messege = null;
		try {
			todoRepository.deleteById(id);
			messege = "delete id'resource";
		} catch (EmptyResultDataAccessException e) {
			messege = "id'resource not found";
		}
		return messege;
	}
	
}
