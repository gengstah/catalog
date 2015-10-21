package org.geeksexception.project.catalog.config;

import org.geeksexception.project.catalog.annotation.HerokuProduction;
import org.geeksexception.project.catalog.annotation.Production;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:server.xml")
@Production
@HerokuProduction
public class ProductionWebServiceConfig {
	
}