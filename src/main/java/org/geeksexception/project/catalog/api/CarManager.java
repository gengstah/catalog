package org.geeksexception.project.catalog.api;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@Path("/{year}/{make}/{model}/{submodel}/{engine}")
	public Car findCar(@NotNull @PathParam("year") Integer year, @NotNull @PathParam("make") String make,
			@NotNull @PathParam("model") String model, @NotNull @PathParam("submodel") String submodel,
			@NotNull @PathParam("engine") String engine) {
		
		return carService.findCar(year, make, model, submodel, engine);
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<Car> findAllCars() {
		
		return carService.findAllCars();
		
	}
	
}