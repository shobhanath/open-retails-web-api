package com.openretails.common.exception;
/**
 * Custom Runtime Exception used for Data Exception(404)
 * @author shobhanath
 *
 */
public class OpenRetailsRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 5556406401856244482L;

	/**
	 * Constructor with a user error message.
	 *
	 * @param str
	 *            error messages
	 */
	public OpenRetailsRuntimeException(String str) {
		super(str);
	}

	/**
	 * Constructor with a user error message and a cause.
	 *
	 * @param str
	 *            The detail message
	 * @param cause
	 *            The exception cause
	 */
	public OpenRetailsRuntimeException(String str, Throwable cause) {
		super(str, cause);
	}

	/**
	 * Constructor with a cause.
	 *
	 * @param cause
	 *            The exception cause
	 */
	public OpenRetailsRuntimeException(Throwable cause) {
		super(cause);
	}

}
