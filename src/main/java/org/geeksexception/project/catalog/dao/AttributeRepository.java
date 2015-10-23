package org.geeksexception.project.catalog.dao;

import java.util.List;

import org.geeksexception.project.catalog.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
	
	@Query("SELECT a FROM Attribute a ORDER BY a.name")
	List<Attribute> findAllAttribute();
	
}