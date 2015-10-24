package org.geeksexception.project.catalog.dao;

import java.util.List;

import org.geeksexception.project.catalog.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
	
	@Query("SELECT a FROM Attribute a ORDER BY a.name")
	List<Attribute> findAllAttribute();
	
	@Query("SELECT a FROM AutoPart ap JOIN ap.attributes a WHERE ap.id = ?1 ORDER BY a.name")
	List<Attribute> findAttributesByAutoPart(Long autoPartId);
	
	@Query("SELECT a FROM Section s JOIN s.defaultAttributes a WHERE s.id = ?1 ORDER BY a.name")
	List<Attribute> findDefaultAttributesOfSection(Long sectionId);
	
}