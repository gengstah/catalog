package org.geeksexception.project.catalog.service.impl;

import javax.inject.Inject;

import org.geeksexception.project.catalog.dao.UserRepository;
import org.geeksexception.project.catalog.model.User;
import org.geeksexception.project.catalog.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	private @Inject UserRepository userRepository;
	
	public UserServiceImpl() { }
	
	@Override
	public User findUserByUsername(String username) {
		
		return userRepository.findUserByUsername(username);
		
	}

}