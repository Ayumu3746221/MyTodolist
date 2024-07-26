package com.example.todo.form;


import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestedMessage {
	private boolean messegeType;
	private List<String> errorMessage;
	
	public RequestedMessage(boolean messageType,List<String> errorMessage) {
		this.messegeType = messageType;
		this.errorMessage = errorMessage;
	}
	
	public List<RequestedMessage> toMessage(){
		 List<RequestedMessage> messages = new ArrayList<>();
		 messages.add(this);
		 return messages;
	}
}
