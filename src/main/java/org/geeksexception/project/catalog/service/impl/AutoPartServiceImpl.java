package org.geeksexception.project.catalog.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.geeksexception.project.catalog.dao.AutoPartRepository;
import org.geeksexception.project.catalog.model.AutoPart;
import org.geeksexception.project.catalog.service.AutoPartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AutoPartServiceImpl implements AutoPartService {
	
	private @Inject AutoPartRepository autoPartRepository;
	
	public AutoPartServiceImpl() { }
	
	@Override
	@Transactional(readOnly = false)
	public AutoPart save(AutoPart autoPart) {
		// TODO: Implement adding of quantity if name and attributes are equal
		return autoPartRepository.save(autoPart);
		
	}

	@Override
	public List<AutoPart> findAutoPartsBySectionAndCar(Long carId, Long sectionId) {
		
		return autoPartRepository.findAutoPartsBySectionAndCar(carId, sectionId);
		
	}

}