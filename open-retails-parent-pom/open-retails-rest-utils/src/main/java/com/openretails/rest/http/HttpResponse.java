package com.openretails.rest.http;

public class HttpResponse {
	private int httpStatusCode;
	private String httpResponseData;
	private String httpResponseError;
	private long httpResponseTimeInMilliSeconds;

	public String getHttpResponseData() {
		return httpResponseData;
	}

	public String getHttpResponseError() {
		return httpResponseError;
	}

	public long getHttpResponseTimeInMilliSeconds() {
		return httpResponseTimeInMilliSeconds;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpResponseData(String httpResponseData) {
		this.httpResponseData = httpResponseData;
	}

	public void setHttpResponseError(String httpResponseError) {
		this.httpResponseError = httpResponseError;
	}

	public void setHttpResponseTimeInMilliSeconds(long httpResponseTimeInMilliSeconds) {
		this.httpResponseTimeInMilliSeconds = httpResponseTimeInMilliSeconds;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
}
