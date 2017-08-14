package com.openretails.common.exception.format;

public class ExceptionMessage {
	private int statusCode;
	private String errorMessage;
	private String resource;
	private String errorCode;
	private String errorType;

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorType() {
		return errorType;
	}

	public String getResource() {
		return resource;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
