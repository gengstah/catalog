package org.geeksexception.project.catalog.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.geeksexception.project.catalog.dao.HeaderRepository;
import org.geeksexception.project.catalog.model.Header;
import org.geeksexception.project.catalog.service.HeaderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HeaderServiceImpl implements HeaderService {
	
	private @Inject HeaderRepository headerRepository;
	
	public HeaderServiceImpl() { }
	
	@Override
	public List<Header> findAllHeaders() {
		
		return headerRepository.findAllHeaders();
		
	}

}