package org.geeksexception.project.catalog.api;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.geeksexception.project.catalog.model.AutoPart;
import org.geeksexception.project.catalog.service.AutoPartService;
import org.springframework.http.MediaType;

@Path("/part")
public class AutoPartManager {
	
	private @Inject AutoPartService autoPartService;
	
	public AutoPartManager() { }
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<String> findAllDistinctAutoPartName() {
		
		return autoPartService.findAllDistinctAutoPartName();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public AutoPart saveAutoPart(@NotNull @Valid AutoPart autoPart) {
		
		return autoPartService.save(autoPart);
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/{carId}/{sectionId}")
	public List<AutoPart> findAutoPartsBySection(@NotNull @PathParam("sectionId") Long sectionId, 
			@QueryParam("page") @Min(1) Integer page, 
			@QueryParam("size") @Min(1) Integer size) {
		
		if(page == null) page = 1;
		if(size == null) size = 20;
		
		return autoPartService.findAutoPartsBySection(sectionId, page, size);
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/{carId}/{sectionId}")
	public List<AutoPart> findAutoPartsBySectionAndCar(@NotNull @PathParam("sectionId") Long sectionId, 
			@NotNull @PathParam("carId") Long carId, 
			@QueryParam("page") @Min(1) Integer page, 
			@QueryParam("size") @Min(1) Integer size) {
		
		if(page == null) page = 1;
		if(size == null) size = 20;
		
		return autoPartService.findAutoPartsBySectionAndCar(sectionId, carId, page, size);
		
	}
	
}