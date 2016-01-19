package org.geeksexception.project.catalog.dao.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.geeksexception.project.catalog.dao.specification.criteria.AutoPartCriteria;
import org.geeksexception.project.catalog.model.AutoPart;
import org.springframework.data.jpa.domain.Specification;

public class AutoPartSpecification {
	
	public static Specification<AutoPart> autoPartsMatchingSearchCriteria(final AutoPartCriteria autoPartCriteria) {
		
		return new Specification<AutoPart>() {

			@Override
			public Predicate toPredicate(Root<AutoPart> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		
	}
	
}