package org.geeksexception.project.catalog.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.geeksexception.project.catalog.dao.AutoPartRepository;
import org.geeksexception.project.catalog.model.AutoPart;
import org.geeksexception.project.catalog.service.AutoPartService;
import org.springframework.data.domain.PageRequest;
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
	public List<AutoPart> findAutoPartsBySection(Long sectionId, Integer page, Integer size) {
		
		return autoPartRepository.findAutoPartsBySection(sectionId, new PageRequest(page, size));
		
	}

	@Override
	public List<AutoPart> findAutoPartsBySectionAndCar(Long sectionId, Long carId, Integer page, Integer size) {
		
		return autoPartRepository.findAutoPartsBySectionAndCar(sectionId, carId, new PageRequest(page, size));
		
	}

	@Override
	public List<String> findAllDistinctAutoPartName() {
		
		return autoPartRepository.findAllDistinctAutoPartName();
		
	}

}