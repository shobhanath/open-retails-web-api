
package com.openretails.common.exception;

public class OpenRetailsBusinessException extends OpenRetailsRuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public OpenRetailsBusinessException(String str) {
		super(str);
	}

	public OpenRetailsBusinessException(String str, Throwable cause) {
		super(str, cause);
	}

	public OpenRetailsBusinessException(Throwable cause) {
		super(cause);
	}

}

