package com.openretails.common.constant;

import java.util.regex.Pattern;

public final class ApplicationConstants {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String EMPTY = "";
	public static final String NORMAL_TXT_NOT_NULL = "Normal text should not be null or blank";
	public static final String ENCRYPTED_TXT_NOT_NULL = "Encrypted text should not be null or blank";
	public static final String BAD_REQUEST = "Bad Request";
	public static final String UNAUTHORIZED = "Unauthorized";
	public static final String FORBIDDEN = "Forbidden";
	public static final String NOT_FOUND = "Not Found";
	public static final String INTERNAL_SERVER_ERROR = "Something wrong in Server";
	public static final String OK = "OK";
	public static final String COLON = ":";
	private ApplicationConstants() {
	}
}
