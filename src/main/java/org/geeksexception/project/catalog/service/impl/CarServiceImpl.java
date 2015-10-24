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
	public List<Car> findAllCars() {
		
		return carRepository.findAllCars();
		
	}

}