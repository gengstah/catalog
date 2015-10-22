package org.geeksexception.project.catalog.dao;

import org.geeksexception.project.catalog.model.Header;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeaderRepository extends JpaRepository<Header, Long> {
	
}