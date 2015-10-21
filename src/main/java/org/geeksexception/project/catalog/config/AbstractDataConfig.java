package org.geeksexception.project.catalog.config;

import java.net.URISyntaxException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

public abstract class AbstractDataConfig {
	
	public abstract DataSource dataSource() throws URISyntaxException;
	
	@Bean
	public EntityManagerFactory entityManagerFactory() throws URISyntaxException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(showSql());
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.mfhsapp.kcvlending.model");
		factory.setJpaProperties(additionalProperties());
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
		
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws URISyntaxException {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		
		return txManager;
		
	}
	
	protected Properties additionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "none");

		return properties;

	}
	
	protected boolean showSql() { return false; }
	
}