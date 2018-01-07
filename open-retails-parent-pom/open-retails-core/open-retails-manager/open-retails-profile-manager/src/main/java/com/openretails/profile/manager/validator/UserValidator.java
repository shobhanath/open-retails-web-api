package com.openretails.profile.manager.validator;

import java.util.Collection;
import java.util.regex.Matcher;

import javax.validation.constraints.Email;

import org.apache.commons.lang3.StringUtils;

import com.openretails.common.constant.ApplicationConstants;
import com.openretails.common.constant.BusinessMessages;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.data.AddressDTO;
import com.openretails.data.UserDTO;

public final class UserValidator {

	public static void fullValidate(AddressDTO address, boolean isUpdate) {
		validateObj(address);
		if (isUpdate) {
			validateId(address.getIdentity());
		}
		if (StringUtils.isBlank(address.getAddressFreeText())) {
			throw new OpenRetailsValidationException(BusinessMessages.VALIDATE_FULL_ADDRESS);
		}
	}

	public static <T> void fullValidate(Collection<T> objects, boolean isUpdate) {
		for (final Object obj : objects) {
			if (obj instanceof AddressDTO) {
				fullValidate((AddressDTO) obj, isUpdate);
			}
		}
	}


	public static boolean isEmailValid(String emailStr) {
		final Matcher matcher = ApplicationConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public static void validateEmailAddress(Collection<String> emails) {
		if (emails == null || emails.isEmpty()) {
			throw new OpenRetailsValidationException(BusinessMessages.VALIDATE_PRIMARY_EMIL_ADDRESS);
		}
		emails.forEach(primaryEmailId -> validateEmailAddress(primaryEmailId));
	}

	public static void validateEmailAddress(@Email String primaryEmailId) {
		if (StringUtils.isBlank(primaryEmailId) || !isEmailValid(primaryEmailId)) {
			throw new OpenRetailsValidationException(BusinessMessages.VALIDATE_PRIMARY_EMIL_ADDRESS);
		}
	}

	public static void validateId(Collection<Long> ids) {
		ids.forEach(id -> validateId(id));
	}

	public static void validateId(Long id) {
		if (id == null || id <= 0) {
			throw new OpenRetailsValidationException(BusinessMessages.VALIDATE_IDENTITY);
		}
	}

	public static void validateObj(AddressDTO address) {
		if (address == null) {
			throw new OpenRetailsValidationException(BusinessMessages.VALIDATE_ADDRESS_OBJ);
		}
	}

	public static void validateObj(UserDTO userDTO) {
		if (userDTO == null) {
			throw new OpenRetailsValidationException(BusinessMessages.VALIDATE_USER_OBJ);
		}
	}

	public static <T> void validateObjAndIdenity(Collection<T> objects) {
		for (final Object obj : objects) {
			if (obj instanceof UserDTO) {
				validateObj((UserDTO) obj);
				validateId(((UserDTO) obj).getIdentity());
			}
			if (obj instanceof AddressDTO) {
				validateObj((AddressDTO) obj);
				validateId(((AddressDTO) obj).getIdentity());
			}
		}
	}

	private UserValidator() {
	}

}
