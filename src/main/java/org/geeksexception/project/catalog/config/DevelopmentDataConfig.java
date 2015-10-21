package org.geeksexception.project.catalog.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.geeksexception.project.catalog.annotation.Development;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@EnableJpaRepositories("org.geeksexception.project.catalog.dao")
@Development
public class DevelopmentDataConfig extends AbstractDataConfig {
	
	@Override
	@Bean
    public DataSource dataSource() {
		
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.build();
        
    }
	
	@Override
	protected boolean showSql() { return true; }
	
	@Override
	public Properties additionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

		return properties;

	}
	
}