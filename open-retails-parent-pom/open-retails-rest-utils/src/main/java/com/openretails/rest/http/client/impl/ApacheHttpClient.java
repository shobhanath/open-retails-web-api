package com.openretails.rest.http.client.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.json.JsonObjectConvertor;
import com.openretails.json.impl.JsonObjectConvertorImpl;
import com.openretails.rest.http.HttpRequest;
import com.openretails.rest.http.HttpResponse;
import com.openretails.rest.http.client.HttpClient;

public class ApacheHttpClient implements HttpClient {
	private DefaultHttpClient httpClient = null;
	private JsonObjectConvertor jsonObjectConvertor = null;

	private void doDelete(HttpDelete httpDelete, HttpRequest request, HttpResponse httpResponse) {
		if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
			for (final Map.Entry<String, String> kv : request.getHeaders().entrySet()) {
				httpDelete.addHeader(kv.getKey(), kv.getValue());
			}
		}
		org.apache.http.HttpResponse response = null;
		try {
			response = httpClient.execute(httpDelete);
		} catch (final ClientProtocolException e) {
			httpResponse.setHttpResponseError(e.getMessage());
		} catch (final IOException e) {
			httpResponse.setHttpResponseError(e.getMessage());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		if (response != null) {
			httpResponse.setHttpStatusCode(response.getStatusLine().getStatusCode());
			final HttpEntity httpEntity = response.getEntity();
			try {
				httpResponse.setHttpResponseData(EntityUtils.toString(httpEntity));
			} catch (final ParseException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			} catch (final IOException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			}

		}
	}


	private void doGet(HttpGet httpGet, HttpRequest request, HttpResponse httpResponse) {
		if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
			for (final Map.Entry<String, String> kv : request.getHeaders().entrySet()) {
				httpGet.addHeader(kv.getKey(), kv.getValue());
			}
		}
		org.apache.http.HttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
		} catch (final ClientProtocolException e) {
			httpResponse.setHttpResponseError(e.getMessage());
		} catch (final IOException e) {
			httpResponse.setHttpResponseError(e.getMessage());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		if (response != null) {
			httpResponse.setHttpStatusCode(response.getStatusLine().getStatusCode());
			final HttpEntity httpEntity = response.getEntity();
			try {
				httpResponse.setHttpResponseData(EntityUtils.toString(httpEntity));
			} catch (final ParseException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			} catch (final IOException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			}

		}
	}

	private void doPost(HttpPost httpPost, HttpRequest request, HttpResponse httpResponse) {
		if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
			for (final Map.Entry<String, String> kv : request.getHeaders().entrySet()) {
				httpPost.addHeader(kv.getKey(), kv.getValue());
			}
		}
		if (request.getHttpRequestData() != null) {
			StringEntity userEntity=null;
			try {
				jsonObjectConvertor = new JsonObjectConvertorImpl();
				userEntity = new StringEntity(jsonObjectConvertor.getJson(request.getHttpRequestData()));
			} catch (final UnsupportedEncodingException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			}
			httpPost.setEntity(userEntity);
		}

		org.apache.http.HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (final ClientProtocolException e) {
			httpResponse.setHttpResponseError(e.getMessage());
		} catch (final IOException e) {
			httpResponse.setHttpResponseError(e.getMessage());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		if (response != null) {
			httpResponse.setHttpStatusCode(response.getStatusLine().getStatusCode());
			final HttpEntity httpEntity = response.getEntity();
			try {
				httpResponse.setHttpResponseData(EntityUtils.toString(httpEntity));
			} catch (final ParseException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			} catch (final IOException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			}

		}
	}


	private void doPut(HttpPut httpPut, HttpRequest request, HttpResponse httpResponse) {
		if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
			for (final Map.Entry<String, String> kv : request.getHeaders().entrySet()) {
				httpPut.addHeader(kv.getKey(), kv.getValue());
			}
		}
		if (request.getHttpRequestData() != null) {
			StringEntity userEntity=null;
			try {
				jsonObjectConvertor = new JsonObjectConvertorImpl();
				userEntity = new StringEntity(jsonObjectConvertor.getJson(request.getHttpRequestData()));
			} catch (final UnsupportedEncodingException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			}
			httpPut.setEntity(userEntity);
		}

		org.apache.http.HttpResponse response = null;
		try {
			response = httpClient.execute(httpPut);
		} catch (final ClientProtocolException e) {
			httpResponse.setHttpResponseError(e.getMessage());
		} catch (final IOException e) {
			httpResponse.setHttpResponseError(e.getMessage());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		if (response != null) {
			httpResponse.setHttpStatusCode(response.getStatusLine().getStatusCode());
			final HttpEntity httpEntity = response.getEntity();
			try {
				httpResponse.setHttpResponseData(EntityUtils.toString(httpEntity));
			} catch (final ParseException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			} catch (final IOException e) {
				httpResponse.setHttpResponseError(e.getMessage());
			}

		}
	}

	@Override
	public HttpResponse execute(HttpRequest request) throws OpenRetailsRuntimeException {
		HttpResponse httpResponse = null;
		if (request != null && request.getHttpRequestMethod() != null
				&& StringUtils.isNotBlank(request.getServiceUri())) {
			switch (request.getHttpRequestMethod()) {
			case GET:
				httpClient = new DefaultHttpClient();
				httpResponse = new HttpResponse();
				final HttpGet httpGet = new HttpGet(request.getServiceUri());
				doGet(httpGet, request, httpResponse);

				break;
			case HEAD:
				break;
			case POST:
				httpClient = new DefaultHttpClient();
				httpResponse = new HttpResponse();
				final HttpPost httpPost = new HttpPost(request.getServiceUri());
				doPost(httpPost, request, httpResponse);
				break;
			case PUT:
				httpClient = new DefaultHttpClient();
				httpResponse = new HttpResponse();
				final HttpPut httpPut = new HttpPut(request.getServiceUri());
				doPut(httpPut, request, httpResponse);
				break;
			case DELETE:
				httpClient = new DefaultHttpClient();
				httpResponse = new HttpResponse();
				final HttpDelete httpDelete = new HttpDelete(request.getServiceUri());
				doDelete(httpDelete, request, httpResponse);
				break;
			case CONNECT:
				break;
			case OPTIONS:
				break;
			case TRACE:
				break;
			}

		}

		return httpResponse;

	}

}
