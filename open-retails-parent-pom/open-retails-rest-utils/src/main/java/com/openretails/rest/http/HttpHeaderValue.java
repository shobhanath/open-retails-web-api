package com.openretails.rest.http;

public enum HttpHeaderValue {
	APPLICATION_JSON("application/json"), APPLICATION_URLENCODED("application/x-www-form-urlencoded");

	private String headerValue;

	HttpHeaderValue(String headerValue) {
		this.headerValue = headerValue;

	}

	public String getHeaderValue() {
		return headerValue;
	}
}
