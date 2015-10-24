package org.geeksexception.project.catalog.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.geeksexception.project.catalog.dao.AutoPartRepository;
import org.geeksexception.project.catalog.model.AutoPart;
import org.geeksexception.project.catalog.service.AutoPartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AutoPartServiceImpl implements AutoPartService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private @Inject AutoPartRepository autoPartRepository;
	
	public AutoPartServiceImpl() { }
	
	@Override
	@Transactional(readOnly = false)
	public AutoPart save(AutoPart autoPart) {
		
		logger.debug("({})", autoPart);
		
		// TODO: Implement adding of quantity if name and attributes are equal
		return autoPartRepository.save(autoPart);
		
	}
	
	@Override
	public List<AutoPart> findAutoPartsBySection(Long sectionId, Integer page, Integer size) {
		
		logger.debug("({}, {}, {})", sectionId, page, size);
		
		return autoPartRepository.findAutoPartsBySection(sectionId, new PageRequest(page, size));
		
	}

	@Override
	public List<AutoPart> findAutoPartsBySectionAndCar(Long sectionId, Long carId, Integer page, Integer size) {
		
		logger.debug("({}, {}, {}, {})", sectionId, carId, page, size);
		
		return autoPartRepository.findAutoPartsBySectionAndCar(sectionId, carId, new PageRequest(page, size));
		
	}

	@Override
	public List<String> findAllDistinctAutoPartName() {
		
		return autoPartRepository.findAllDistinctAutoPartName();
		
	}

}