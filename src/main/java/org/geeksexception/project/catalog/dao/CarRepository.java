package org.geeksexception.project.catalog.dao;

import java.util.List;

import org.geeksexception.project.catalog.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long> {
	
	@Query("SELECT c FROM Car c WHERE c.year = ?1 AND c.make = ?2 AND c.model = ?3 AND c.submodel = ?4 AND c.engine = ?5")
	Car findCar(Integer year, String make, String model, String submodel, String engine);
	
	@Query("SELECT DISTINCT c.make FROM Car c WHERE c.year = ?1 ORDER BY c.make")
	List<String> retrieveMakesUsingYear(Integer year);
	
	@Query("SELECT DISTINCT c.model FROM Car c WHERE c.year = ?1 AND c.make = ?2 ORDER BY c.model")
	List<String> retrieveModelsUsingMake(Integer year, String make);
	
	@Query("SELECT DISTINCT c.submodel FROM Car c WHERE c.year = ?1 AND c.make = ?2 AND c.model = ?3 ORDER BY c.submodel")
	List<String> retrieveSubmodelsUsingModel(Integer year, String make, String model);
	
	@Query("SELECT DISTINCT c.engine FROM Car c WHERE c.year = ?1 AND c.make = ?2 AND c.model = ?3 AND c.submodel = ?4 ORDER BY c.engine")
	List<String> retrieveEnginesUsingSubmodel(Integer year, String make, String model, String submodel);
	
}