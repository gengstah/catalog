package org.geeksexception.project.catalog.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.geeksexception.project.catalog.dao.SectionRepository;
import org.geeksexception.project.catalog.model.Section;
import org.geeksexception.project.catalog.service.SectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SectionServiceImpl implements SectionService {
	
	private @Inject SectionRepository sectionRepository;
	
	public SectionServiceImpl() { }
	
	@Override
	public List<Section> findSectionsByHeader(Long headerId) {
		
		return sectionRepository.findSectionsByHeader(headerId);
		
	}

	@Override
	public List<String> findAllDistinctSectionName() {
		
		return sectionRepository.findAllDistinctSectionName();
		
	}

}