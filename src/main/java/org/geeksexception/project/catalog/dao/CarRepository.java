package org.geeksexception.project.catalog.dao;

import java.util.List;

import org.geeksexception.project.catalog.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long> {
	
	@Query("SELECT c FROM Car c WHERE c.year = ?1 AND c.make = ?2 AND c.model = ?3 AND c.submodel = ?4 AND c.engine = ?5")
	Car findCar(Integer year, String make, String model, String submodel, String engine);
	
	@Query("SELECT c FROM Car c ORDER BY c.year, c.make, c.model, c.submodel, c.engine")
	List<Car> findAllCars();
	
}