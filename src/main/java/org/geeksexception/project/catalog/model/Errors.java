package org.geeksexception.project.catalog.model;

import java.util.ArrayList;
import java.util.List;

public class Errors {
	
	private List<Error> errors;
	
	public Errors() { errors = new ArrayList<Error>(); }

	public List<Error> getErrors() {
		return errors;
	}

	public Errors addError(Error error) {
		errors.add(error);
		return this;
	}
	
	@Override
	public String toString() {
		String delimiter = "";
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		for(Error error : errors) {
			sb.append(delimiter)
				.append(error);
			delimiter = ",";
		}
		sb.append("]");
		
		return sb.toString();
	}
	
}