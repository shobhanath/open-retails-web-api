package com.openretails.common.constant;

import java.util.regex.Pattern;

public interface ApplicationConstants extends SpringBeanIds, BusinessMessages, DataAccessMessages {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

}
