package org.geeksexception.project.catalog.api;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.geeksexception.project.catalog.model.Attribute;
import org.geeksexception.project.catalog.service.AttributeService;
import org.springframework.http.MediaType;

@Path("/attr")
public class AttributeManager {
	
	private @Inject AttributeService attributeService;
	
	public AttributeManager() { }
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<Attribute> findAllAttribute() {
		
		return attributeService.findAllAttribute();
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/part/{autoPartId}")
	public List<Attribute> findAttributesByAutoPart(@NotNull @PathParam("autoPartId") Long autoPartId) {
		
		return attributeService.findAttributesByAutoPart(autoPartId);
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/section/{sectionId}")
	public List<Attribute> findDefaultAttributesOfSection(@NotNull @PathParam("sectionId") Long sectionId) {
		
		return attributeService.findDefaultAttributesOfSection(sectionId);
		
	}
	
}