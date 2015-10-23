package org.geeksexception.project.catalog.api;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.geeksexception.project.catalog.model.Section;
import org.geeksexception.project.catalog.service.SectionService;
import org.springframework.http.MediaType;

@Path("/section")
public class SectionManager {
	
	private @Inject SectionService sectionService;
	
	public SectionManager() { }
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<String> findAllDistinctSectionName() {
		
		return sectionService.findAllDistinctSectionName();
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/{headerId}")
	public List<Section> findSectionsByHeader(@NotNull @PathParam("headerId") Long headerId) {
		
		return sectionService.findSectionsByHeader(headerId);
		
	}
	
}