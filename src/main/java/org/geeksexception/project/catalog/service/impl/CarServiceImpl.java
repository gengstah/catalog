package org.geeksexception.project.catalog.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.geeksexception.project.catalog.dao.CarRepository;
import org.geeksexception.project.catalog.model.Car;
import org.geeksexception.project.catalog.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
	
	private @Inject CarRepository carRepository;
	
	@Override
	public Car findCar(Integer year, String make, String model, String submodel, String engine) {
		
		return carRepository.findCar(year, make, model, submodel, engine);
		
	}

	@Override
	public List<String> retrieveMakesUsingYear(Integer year) {
		
		return carRepository.retrieveMakesUsingYear(year);
		
	}

	@Override
	public List<String> retrieveModelsUsingMake(String make) {
		
		return carRepository.retrieveModelsUsingMake(make);
		
	}

	@Override
	public List<String> retrieveSubmodelsUsingModel(String model) {
		
		return carRepository.retrieveSubmodelsUsingModel(model);
		
	}

	@Override
	public List<String> retrieveEnginesUsingSubmodel(String submodel) {
		
		return carRepository.retrieveEnginesUsingSubmodel(submodel);
		
	}

}