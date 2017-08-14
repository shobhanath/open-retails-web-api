package com.openretails.rest.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class HttpRequest {
	private Object httpRequestData;
	private String serviceUri;
	private HttpRequestMethod httpRequestMethod;

	private Map<String, String> headers = new HashMap<String, String>();

	public Map<String, String> getHeaders() {
		return headers;
	}

	public Object getHttpRequestData() {
		return httpRequestData;
	}

	public HttpRequestMethod getHttpRequestMethod() {
		return httpRequestMethod;
	}

	public String getServiceUri() {
		return serviceUri;
	}

	private void httpHeaderNullChecker(HttpHeaderKey key, HttpHeaderValue value) {
		if (key != null && value != null && StringUtils.isNotBlank(key.getHeaderKey())
				&& StringUtils.isNotBlank(value.getHeaderValue())) {
			headers.put(key.getHeaderKey(), value.getHeaderValue());
		}
	}

	public void setHeaders(Map<String, String> headers) {
		if (headers != null && !headers.isEmpty()) {
			this.headers = headers;
		}
	}

	public void setHeaders(String key, String value) {
		if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
			headers.put(key, value);
		}
	}

	public void setHttpHeader(HttpHeaderKey key, HttpHeaderValue value) {
		httpHeaderNullChecker(key, value);
	}

	public void setHttpHeaders(Map<HttpHeaderKey, HttpHeaderValue> headers) {
		if (headers != null && !headers.isEmpty()) {
			for (final Map.Entry<HttpHeaderKey, HttpHeaderValue> entry : headers.entrySet()) {
				httpHeaderNullChecker(entry.getKey(), entry.getValue());
			}
		}
	}

	public void setHttpRequestData(Object httpRequestData) {
		this.httpRequestData = httpRequestData;
	}

	public void setHttpRequestMethod(HttpRequestMethod httpRequestMethod) {
		this.httpRequestMethod = httpRequestMethod;
	}

	public void setServiceUri(String serviceUri) {
		this.serviceUri = serviceUri;
	}
}
