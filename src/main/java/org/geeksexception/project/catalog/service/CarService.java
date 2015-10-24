package org.geeksexception.project.catalog.service;

import java.util.List;

import org.geeksexception.project.catalog.model.Car;

public interface CarService {
	
	Car findCar(Integer year, String make, String model, String submodel, String engine);
	
	List<Car> findAllCars();
	
}