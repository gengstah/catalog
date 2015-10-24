package org.geeksexception.project.catalog.service;

import java.util.List;

import org.geeksexception.project.catalog.model.Attribute;

public interface AttributeService {
	
	List<Attribute> findAllAttribute();
	
	List<Attribute> findAttributesByAutoPart(Long autoPartId);
	
	List<Attribute> findDefaultAttributesOfSection(Long sectionId);
	
}