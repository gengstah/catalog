package org.geeksexception.project.catalog.service;

import java.util.List;

import org.geeksexception.project.catalog.model.AutoPart;

public interface AutoPartService {
	
	AutoPart save(AutoPart autoPart);
	
	List<AutoPart> findAutoPartsBySection(Long sectionId, Integer page, Integer size);
	
	List<AutoPart> findAutoPartsBySectionAndCar(Long sectionId, Long carId, Integer page, Integer size);
	
	List<String> findAllDistinctAutoPartName();
	
}