package org.geeksexception.project.catalog.config;

import org.geeksexception.project.catalog.annotation.HerokuProduction;
import org.geeksexception.project.catalog.annotation.Production;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebMvc
@Production
@HerokuProduction
public class ProductionWebMvcConfig extends AbstractWebMvcConfig {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
		registry.addResourceHandler("/bootstrap/**").addResourceLocations("/bootstrap/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
		registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
		registry.addResourceHandler("/style-switcher/**").addResourceLocations("/style-switcher/");
	}
	
}