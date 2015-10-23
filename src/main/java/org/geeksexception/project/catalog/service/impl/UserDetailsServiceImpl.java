package org.geeksexception.project.catalog.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.geeksexception.project.catalog.enums.UserStatus;
import org.geeksexception.project.catalog.model.User;
import org.geeksexception.project.catalog.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private @Inject UserService userService;
	
	public UserDetailsServiceImpl() { }
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		User user = userService.findUserByUsername(username);
		
		if(user == null) throw new UsernameNotFoundException("User Not Found!");
		else {
			String password = user.getPassword();
			boolean enabled = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean credentialsNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonLocked = user.getStatus().equals(UserStatus.ACTIVE);
			
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
			
			org.springframework.security.core.userdetails.User securityUser =
					new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			
			return securityUser;
			
		}
		
	}
	
}