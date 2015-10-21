package org.geeksexception.project.catalog.config;

import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.geeksexception.project.catalog.annotation.Production;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("org.geeksexception.project.catalog.dao")
@Production
public class ProductionDataConfig extends AbstractDataConfig {
	
	@Override
	@Bean
    public DataSource dataSource() throws URISyntaxException {
		
        String dbName = System.getProperty("RDS_DB_NAME");
        String username = System.getProperty("RDS_USERNAME");
        String password = System.getProperty("RDS_PASSWORD");
        String hostname = System.getProperty("RDS_HOSTNAME");
        String port = System.getProperty("RDS_PORT");
        String dbUrl = "jdbc:postgresql://" + hostname + ':' + port + "/" + dbName;

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setDriverClassName("org.postgresql.Driver");

        return basicDataSource;
        
    }
	
	@Override
	protected Properties additionalProperties() {
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", System.getProperty("hbm2ddl"));
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

		return properties;
		
	}
	
}