package org.geeksexception.project.catalog.api;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.geeksexception.project.catalog.model.AutoPart;
import org.geeksexception.project.catalog.service.AutoPartService;
import org.springframework.http.MediaType;

@Path("/part")
public class AutoPartManager {
	
	private @Inject AutoPartService autoPartService;
	
	public AutoPartManager() { }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public AutoPart saveAutoPart(@NotNull @Valid AutoPart autoPart) {
		
		return autoPartService.saveAutoPart(autoPart);
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/{carId}/{sectionId}")
	public List<AutoPart> findAutoPartsBySectionAndCar(@NotNull @PathParam("carId") Long carId, @NotNull @PathParam("sectionId") Long sectionId) {
		
		return autoPartService.findAutoPartsBySectionAndCar(carId, sectionId);
	}
	
}