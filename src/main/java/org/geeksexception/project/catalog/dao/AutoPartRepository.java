package org.geeksexception.project.catalog.dao;

import java.util.List;

import org.geeksexception.project.catalog.model.AutoPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AutoPartRepository extends JpaRepository<AutoPart, Long> {
	
	@Query("SELECT ap FROM AutoPart ap JOIN ap.compatibleCars c WHERE c.id = ?1 AND ap.section.id = ?2")
	List<AutoPart> findAutoPartsBySectionAndCar(Long carId, Long sectionId);
	
	@Query("SELECT DISTINCT ap.name FROM AutoPart ap ORDER BY ap.name")
	List<String> findAllDistinctAutoPartName();
	
}