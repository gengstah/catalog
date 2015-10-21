package org.geeksexception.project.catalog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

/**
 * Annotation for Production environment
 * 
 * @author Gerard DE LAS ARMAS
 * @version %I%, %G%
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile("heroku-production")
public @interface HerokuProduction {
}