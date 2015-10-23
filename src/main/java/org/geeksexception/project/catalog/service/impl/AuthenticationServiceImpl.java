package org.geeksexception.project.catalog.service.impl;

import javax.inject.Inject;

import org.geeksexception.project.catalog.exception.CatalogServiceApiException;
import org.geeksexception.project.catalog.model.Errors;
import org.geeksexception.project.catalog.model.Error;
import org.geeksexception.project.catalog.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private @Inject AuthenticationManager authenticationManager;
	
	@Override
	public void authenticate(String username, String password) throws CatalogServiceApiException {
		
		Authentication request = new UsernamePasswordAuthenticationToken(username, password);
		Authentication result = null;
		
		try {
			result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch(AuthenticationException e) {
			logger.error("Authentication failed: {}", e.getMessage());
			throw new CatalogServiceApiException(
					"Error while authenticating", 
					new Errors()
						.addError(new Error("email", e.getMessage()))
						.addError(new Error("password", e.getMessage())),
					e);
		}
		
	}

}