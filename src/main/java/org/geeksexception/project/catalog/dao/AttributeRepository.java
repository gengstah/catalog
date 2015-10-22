package org.geeksexception.project.catalog.dao;

import org.geeksexception.project.catalog.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}