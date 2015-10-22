package org.geeksexception.project.catalog.hibernate.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
 
public class HibernateAwareObjectMapper extends ObjectMapper {
	
	private static final long serialVersionUID = -2930962735782618594L;

	public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        registerModule(hm);
    }
	
}