package com.openretails.json.impl;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.json.JsonObjectConvertor;

public class JsonObjectConvertorImpl implements JsonObjectConvertor {

	private ObjectMapper mapper = null;

	@Override
	public <T> T fromJSON(final TypeReference<T> type, final String jsonPacket) {
		T data = null;
		try {
			mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			data = mapper.readValue(jsonPacket, type);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public String getJson(Object object) throws OpenRetailsRuntimeException {
		String jsonString = null;
		if (object != null) {
			mapper = new ObjectMapper();
			try {
				jsonString = mapper.writeValueAsString(object);
			} catch (final JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return jsonString;
	}


	@Override
	public Object getObject(String jsonString, Object object) throws OpenRetailsRuntimeException {
		Object obj = null;
		if (StringUtils.isNotBlank(jsonString)) {
			mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				obj = mapper.readValue(jsonString, object.getClass());
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

}
