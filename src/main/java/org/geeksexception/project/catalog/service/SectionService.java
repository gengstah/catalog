package org.geeksexception.project.catalog.service;

import java.util.List;

import org.geeksexception.project.catalog.model.Section;

public interface SectionService {
	
	List<Section> findSectionsByHeader(Long headerId);
	
	List<String> findAllDistinctSectionName();
	
}