package org.geeksexception.project.catalog.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CatalogServiceApiExceptionHandler implements 
		ExceptionMapper<CatalogServiceApiException> {
	
	public Response toResponse(CatalogServiceApiException exception) {
		Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
		
        return Response.status(status).entity(exception)
        		.header("X-KCVLendingServiceApi-Exception", exception)
        		.build();
	}
	
}