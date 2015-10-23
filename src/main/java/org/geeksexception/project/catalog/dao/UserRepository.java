package org.geeksexception.project.catalog.dao;

import org.geeksexception.project.catalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	User findUserByUsername(String username);
	
}