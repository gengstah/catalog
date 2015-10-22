package org.geeksexception.project.catalog.dao;

import org.geeksexception.project.catalog.model.AutoPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoPartRepository extends JpaRepository<AutoPart, Long> {
	
}