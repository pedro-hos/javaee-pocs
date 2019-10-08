package com.redhat.model;

import java.util.Optional;

public class Message {

	public String message;
	
	public Message () {}
	
	public Message (final Optional<String> message) {
		this.message = message.get();
	}
}
