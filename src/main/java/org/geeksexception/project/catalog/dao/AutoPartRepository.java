package org.geeksexception.project.catalog.dao;

import java.util.List;

import org.geeksexception.project.catalog.model.AutoPart;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AutoPartRepository extends JpaRepository<AutoPart, Long>, JpaSpecificationExecutor<AutoPart> {
	
	@Query("SELECT ap FROM AutoPart ap WHERE ap.section.id = ?1")
	List<AutoPart> findAutoPartsBySection(Long sectionId, Pageable pageable);
	
	@Query("SELECT ap FROM AutoPart ap JOIN ap.compatibleCars c WHERE ap.section.id = ?1 AND c.id = ?2")
	List<AutoPart> findAutoPartsBySectionAndCar(Long sectionId, Long carId, Pageable pageable);
	
	@Query("SELECT DISTINCT ap.name FROM AutoPart ap ORDER BY ap.name")
	List<String> findAllDistinctAutoPartName();
	
}