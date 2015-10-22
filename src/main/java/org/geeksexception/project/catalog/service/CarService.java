package org.geeksexception.project.catalog.service;

import java.util.List;

import org.geeksexception.project.catalog.model.Car;

public interface CarService {
	
	Car findCar(Integer year, String make, String model, String submodel, String engine);
	
	List<String> retrieveMakesUsingYear(Integer year);
	
	List<String> retrieveModelsUsingMake(Integer year, String make);
	
	List<String> retrieveSubmodelsUsingModel(Integer year, String make, String model);
	
	List<String> retrieveEnginesUsingSubmodel(Integer year, String make, String model, String submodel);
	
}