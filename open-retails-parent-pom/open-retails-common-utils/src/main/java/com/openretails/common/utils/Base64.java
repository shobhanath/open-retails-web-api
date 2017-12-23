package com.openretails.common.utils;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

import com.openretails.common.constant.ApplicationConstants;
import com.openretails.common.exception.OpenRetailsValidationException;

public final class Base64 {

	public static final String decode(String encryptedText) {
		if (StringUtils.isBlank(encryptedText)) {
			throw new OpenRetailsValidationException(ApplicationConstants.ENCRYPTED_TXT_NOT_NULL);
		}
		return new String(java.util.Base64.getDecoder().decode(encryptedText));
	}

	public static final String encode(String normalText) {
		if (StringUtils.isBlank(normalText)) {
			throw new OpenRetailsValidationException(ApplicationConstants.NORMAL_TXT_NOT_NULL);
		}
		return java.util.Base64.getEncoder().encodeToString(normalText.getBytes(StandardCharsets.UTF_8));
	}

	private Base64() {
	}
}
