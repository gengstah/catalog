package org.geeksexception.project.catalog.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.geeksexception.project.catalog.model.Header;
import org.geeksexception.project.catalog.service.HeaderService;
import org.springframework.http.MediaType;

@Path("/header")
public class HeaderManager {
	
	private @Inject HeaderService headerService;
	
	public HeaderManager() { }
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<Header> findAllHeaders() {
		
		return headerService.findAllHeaders();
		
	}
	
}