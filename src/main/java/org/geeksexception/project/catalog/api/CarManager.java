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

import org.geeksexception.project.catalog.model.Car;
import org.geeksexception.project.catalog.service.CarService;
import org.springframework.http.MediaType;

@Path("/car")
public class CarManager {
	
	private @Inject CarService carService;
	
	public CarManager() { }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Car findCar(@NotNull @Valid Car car) {
		
		return carService.findCar(car.getYear(), car.getMake(), car.getModel(), car.getSubmodel(), car.getEngine());
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/{year}")
	public List<String> findMakesOfYear(@NotNull @PathParam("year") Integer year) {
		
		return carService.retrieveMakesUsingYear(year);
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/{year}/{make}")
	public List<String> findModelsOfMake(@NotNull @PathParam("year") Integer year, @NotNull @PathParam("make") String make) {
		
		return carService.retrieveModelsUsingMake(year, make);
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/{year}/{make}/{model}")
	public List<String> findSubmodelsOfModel(@NotNull @PathParam("year") Integer year, @NotNull @PathParam("make") String make, @NotNull @PathParam("model") String model) {
		
		return carService.retrieveSubmodelsUsingModel(year, make, model);
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/{year}/{make}/{model}/{submodel}")
	public List<String> findEnginesOfSubmodels(@NotNull @PathParam("year") Integer year, @NotNull @PathParam("make") String make, @NotNull @PathParam("model") String model, @NotNull @PathParam("submodel") String submodel) {
		
		return carService.retrieveEnginesUsingSubmodel(year, make, model, submodel);
		
	}
	
}