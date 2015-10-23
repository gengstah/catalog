package org.geeksexception.project.catalog.service;

import org.geeksexception.project.catalog.exception.CatalogServiceApiException;

public interface AuthenticationService {
	
	void authenticate(String username, String password) throws CatalogServiceApiException;
	
}