package com.openretails.common.exception;

public class OpenRetailsException extends Exception {
	private static final long serialVersionUID = 5556406401856244482L;

	/**
	 * Constructor with a user error message.
	 *
	 * @param str
	 *            error messages
	 */
	public OpenRetailsException(String str) {
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
	public OpenRetailsException(String str, Throwable cause) {
		super(str, cause);
	}

	/**
	 * Constructor with a cause.
	 *
	 * @param cause
	 *            The exception cause
	 */
	public OpenRetailsException(Throwable cause) {
		super(cause);
	}

}
