package org.geeksexception.project.catalog.exception;

import org.geeksexception.project.catalog.model.Errors;

public class CatalogServiceApiException extends Exception {
	
	private static final long serialVersionUID = 983066496162295401L;
	
	private Errors detail;
	
	public CatalogServiceApiException() { }
	
	public CatalogServiceApiException(String message, Errors detail) {
		super(message);
		this.detail = detail;
	}
	
	public CatalogServiceApiException(String message, Errors detail, Throwable cause) {
		super(message, cause);
		this.detail = detail;
	}
	
	public Errors getFaultInfo() { return detail; }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return detail.toString();
	}
	
}