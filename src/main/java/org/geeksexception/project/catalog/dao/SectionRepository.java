package org.geeksexception.project.catalog.dao;

import org.geeksexception.project.catalog.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {

}