package org.geeksexception.project.catalog.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.geeksexception.project.catalog.dao.AttributeRepository;
import org.geeksexception.project.catalog.model.Attribute;
import org.geeksexception.project.catalog.service.AttributeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AttributeServiceImpl implements AttributeService {
	
	private @Inject AttributeRepository attributeRepository;
	
	public AttributeServiceImpl() { }
	
	@Override
	public List<Attribute> findAllAttribute() {
		
		return attributeRepository.findAllAttribute();
		
	}

}