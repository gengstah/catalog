package org.geeksexception.project.catalog.service;

import java.util.List;

import org.geeksexception.project.catalog.model.AutoPart;

public interface AutoPartService {
	
	AutoPart save(AutoPart autoPart);
	
	List<AutoPart> findAutoPartsBySectionAndCar(Long carId, Long sectionId);
	
}