package org.geeksexception.project.catalog.dao;

import java.util.List;

import org.geeksexception.project.catalog.model.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HeaderRepository extends JpaRepository<Header, Long> {
	
	@Query("SELECT h FROM Header h ORDER BY h.name")
	List<Header> findAllHeaders();
	
}