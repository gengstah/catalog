package org.geeksexception.project.catalog.config;

import org.geeksexception.project.catalog.annotation.Development;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:dev/server.xml")
@Development
public class DevelopmentWebServiceConfig {
	
}