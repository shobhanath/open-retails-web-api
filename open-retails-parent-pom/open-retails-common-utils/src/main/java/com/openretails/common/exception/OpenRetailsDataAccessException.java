package com.openretails.common.exception;

public class OpenRetailsDataAccessException extends OpenRetailsRuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public OpenRetailsDataAccessException(String str) {
		super(str);
	}

	public OpenRetailsDataAccessException(String str, Throwable cause) {
		super(str, cause);
	}

	public OpenRetailsDataAccessException(Throwable cause) {
		super(cause);
	}

}
