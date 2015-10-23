package org.geeksexception.project.catalog.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.geeksexception.project.catalog.enums.UserStatus;
import org.geeksexception.project.catalog.exception.CatalogServiceApiException;
import org.geeksexception.project.catalog.model.Errors;
import org.geeksexception.project.catalog.model.Error;
import org.geeksexception.project.catalog.model.User;
import org.geeksexception.project.catalog.service.AuthenticationService;
import org.geeksexception.project.catalog.service.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.http.MediaType;

@Path("/user")
public class UserManager {
	
	private @Inject UserService userService;
	
	private @Inject AuthenticationService authenticationService;
	
	public UserManager() { }
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/authenticate")
	public User authenticateAndLoadUser(
			@NotEmpty(message = "username must not be empty") @FormParam("username") String username, 
			@NotEmpty(message = "password must not be empty") @FormParam("password") String password) 
			throws CatalogServiceApiException {
		
		User user = userService.findUserByUsername(username);
		if(user != null && user.getStatus() != UserStatus.ACTIVE)
			throw new CatalogServiceApiException("User is not active", new Errors().addError(new Error("email", "User is not active")));
		
		authenticationService.authenticate(username, password);
		
		return user;
		
	}
	
}