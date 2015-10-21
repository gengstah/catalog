package org.geeksexception.project.catalog.config;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

@Configuration
@PropertySource(value = {"classpath:/user-mail.properties", "classpath:/mail.properties"})
public class MailConfig {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private @Inject Environment env;

    public MailConfig() { }
    
    @Bean
    public JavaMailSender mailSender() throws IOException {
    	
    	logger.debug("Setting up mail sender");
    	
    	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(env.getProperty("mail.username"));
        javaMailSender.setPassword(env.getProperty("mail.password"));
        
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", env.getProperty("mail.smtp.host"));
        props.setProperty("mail.smtp.port", env.getProperty("mail.smtp.port"));
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        
        javaMailSender.setJavaMailProperties(props);

        return javaMailSender;
    }
    
    @Bean
    public VelocityEngineFactoryBean velocityEngine() {
    	VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
    	Properties velocityProperties = new Properties();
    	velocityProperties.setProperty("resource.loader", "class");
    	velocityProperties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    	
    	velocityEngine.setVelocityProperties(velocityProperties);
    	
    	return velocityEngine;
    }
    
    @Bean
    public SimpleMailMessage template() {
    	SimpleMailMessage msg = new SimpleMailMessage();
    	msg.setFrom(env.getProperty("mail.username"));
    	msg.setSubject("KCV Loans");
    	
    	return msg;
    }
    
}