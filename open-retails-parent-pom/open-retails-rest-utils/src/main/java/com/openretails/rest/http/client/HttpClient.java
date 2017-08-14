package com.openretails.rest.http.client;

import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.rest.http.HttpRequest;
import com.openretails.rest.http.HttpResponse;

public interface HttpClient {
	HttpResponse execute(HttpRequest request) throws OpenRetailsRuntimeException;

}
