package com.revature.dto;

import java.util.Objects;

public class ExceptionMessageDTO {

	private String message;
	
	// constructor
	public ExceptionMessageDTO() {
		
	}
	
	// constructor to set message based on the message in the constructor
	public ExceptionMessageDTO(Exception e) {
		this.message = e.getMessage();
	}
	
	// Methods
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionMessageDTO other = (ExceptionMessageDTO) obj;
		return Objects.equals(message, other.message);
	}

	@Override
	public String toString() {
		return "ExceptionMessageDTO [message=" + message + "]";
	}

	
}
