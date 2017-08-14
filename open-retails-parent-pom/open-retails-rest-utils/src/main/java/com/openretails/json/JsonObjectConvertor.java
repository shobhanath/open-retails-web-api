package com.openretails.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.openretails.common.exception.OpenRetailsRuntimeException;

public interface JsonObjectConvertor {

	<T> T fromJSON(final TypeReference<T> type,
		      final String jsonString) throws OpenRetailsRuntimeException;

	String getJson(Object object) throws OpenRetailsRuntimeException;

	Object getObject(String jsonString, Object object) throws OpenRetailsRuntimeException;

}
