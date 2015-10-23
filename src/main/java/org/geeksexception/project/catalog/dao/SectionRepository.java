package org.geeksexception.project.catalog.dao;

import java.util.List;

import org.geeksexception.project.catalog.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SectionRepository extends JpaRepository<Section, Long> {
	
	@Query("SELECT s FROM Section s WHERE s.header.id = ?1")
	List<Section> findSectionsByHeader(Long headerId);
	
	@Query("SELECT DISTINCT s.name FROM Section s ORDER BY s.name")
	List<String> findAllDistinctSectionName();
	
}