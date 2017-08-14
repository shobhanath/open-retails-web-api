package com.openretails.rest.http;

public enum HttpHeaderKey {
	ACCEPT("Accept"), CONTENT_TYPE("Content-Type"), AUTHORIZATION("Authorization");

	private String headerKey;

	HttpHeaderKey(String headerKey) {
		this.headerKey = headerKey;

	}

	public String getHeaderKey() {
		return headerKey;
	}
}
