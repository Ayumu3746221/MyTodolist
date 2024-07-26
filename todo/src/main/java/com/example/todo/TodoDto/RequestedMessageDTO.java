package com.example.todo.TodoDto;


import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestedMessageDTO {
	private boolean messegeType;
	private List<String> errorMessage;
	
	public RequestedMessageDTO(boolean messageType,List<String> errorMessage) {
		this.messegeType = messageType;
		this.errorMessage = errorMessage;
	}
	
	public List<RequestedMessageDTO> toMessage(){
		 List<RequestedMessageDTO> messages = new ArrayList<>();
		 messages.add(this);
		 return messages;
	}
}
