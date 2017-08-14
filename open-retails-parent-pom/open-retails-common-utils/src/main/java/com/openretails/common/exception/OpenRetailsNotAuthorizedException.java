package com.openretails.common.exception;
/**
 * Custom Runtime Exception used for Bad Request(400)
 * @author shobhanath
 *
 */
public class OpenRetailsNotAuthorizedException extends OpenRetailsRuntimeException {


	private static final long serialVersionUID = 5556406401856244482L;

	/**
	 * Constructor with a user error message.
	 *
	 * @param str
	 *            error messages
	 */
	public OpenRetailsNotAuthorizedException(String str) {
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
	public OpenRetailsNotAuthorizedException(String str, Throwable cause) {
		super(str, cause);
	}

	/**
	 * Constructor with a cause.
	 *
	 * @param cause
	 *            The exception cause
	 */
	public OpenRetailsNotAuthorizedException(Throwable cause) {
		super(cause);
	}


}
