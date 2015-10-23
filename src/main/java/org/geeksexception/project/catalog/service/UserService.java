package org.geeksexception.project.catalog.service;

import org.geeksexception.project.catalog.model.User;

public interface UserService {
	
	User findUserByUsername(String username);
	
}