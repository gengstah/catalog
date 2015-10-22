package org.geeksexception.project.catalog.model;

public class Error {
	
	private String field;
	
	private String message;
	
	public Error() { }
	
	public Error(String message) {
		this.message = message;
	}
	
	public Error(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("{ ")
				.append("\"field\" : \"").append(field).append("\", ")
				.append("\"message\" : \"").append(message).append("\" ")
				.append("}")
			.toString();
	}
	
}