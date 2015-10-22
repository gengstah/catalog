package org.geeksexception.project.catalog.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.cxf.validation.ResponseConstraintViolationException;
import org.geeksexception.project.catalog.model.Errors;
import org.geeksexception.project.catalog.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationExceptionMapper implements
		ExceptionMapper<ValidationException> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Response toResponse(ValidationException exception) {
		if (exception instanceof ConstraintViolationException) { 
            
            final ConstraintViolationException constraint = (ConstraintViolationException) exception;
            final boolean isResponseException = constraint instanceof ResponseConstraintViolationException;
            
            Errors errors = new Errors();
            for (final ConstraintViolation< ? > violation: constraint.getConstraintViolations()) {
                logger.warn("{}.{}:{}",
                		violation.getRootBeanClass().getSimpleName(),
                		violation.getPropertyPath(),
                		violation.getMessage());
                errors.addError(new Error(violation.getPropertyPath().toString(), violation.getMessage()));
            }
            
            if (isResponseException) {
                return Response
                		.status(Response.Status.INTERNAL_SERVER_ERROR)
                		.entity(exception)
                		.build();
            }
            
            return Response
            		.status(Response.Status.BAD_REQUEST).entity(errors)
            		.header("X-KCVLendingServiceApi-Exception", errors)
            		.build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception).build();
        }
		
	}
	
}