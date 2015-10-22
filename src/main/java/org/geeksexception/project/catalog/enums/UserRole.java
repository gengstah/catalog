package org.geeksexception.project.catalog.enums;

public enum UserRole {
	ROLE_ADMIN("Admin"),ROLE_ENCODER("Encoder"),ROLE_MEMBER("Member");
	
	private String description;
	
	private UserRole(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
}