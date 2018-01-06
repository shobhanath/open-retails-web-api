package com.openretails.data.validation;

public final class UserValidationMessages {

	public static final String VALIDATE_IDENTITY = "Invalid identity, it should be greater than zero";
	public static final String VALIDATE_IDENTITY_NULL = "Invalid identity, it should be null";
	public static final String VALIDATE_OBSOLETE_NOT_NULL = "Invalid obsolete, it should not be null. Possible values should be either true or false";
	public static final String VALIDATE_OBSOLETE = "Invalid obsolete, it should be true";
	public static final String VALIDATE_AGE = "Age should be minimum 18 and maximum 60 years";
	public static final String VALIDATE_PRIMARY_MOBILE_NUMBER = "Primary mobile number must be 10 digits number";
	public static final String VALIDATE_FIRST_NAME = "First name should not be null or empty";
	public static final String VALIDATE_LAST_NAME = "Last name should not be null or empty";
	public static final String VALIDATE_PASSWORD = "Password should not be null or empty";
	public static final String VALIDATE_PRIMARY_EMIL_ADDRESS = "Primary email address should not be empty or invalid";
	public static final String VALIDATE_USER_OBJ = "User object should not be null or empty";
	public static final String VALIDATE_USER_PASSWORD = "Username and password should not be null or empty";
	public static final String VALIDATE_FIRST_NAME_SIZE = "First name should be minimum 2 and maximum 255 character";
	public static final String VALIDATE_MIDDLE_NAME_SIZE = "Middle name should be minimum 2 and maximum 255 character";
	public static final String VALIDATE_LAST_NAME_SIZE = "Last name should be minimum 2 and maximum 255 character";
	public static final String VALIDATE_USER_TYPE = "User type should not be null. Possible values are ADMIN_USER, END_USER";
	public static final String VALIDATE_PASSWORD_SIZE = "Password should be minimum 6 and maximum 20 character";
	public static final String VALIDATE_PASSWORD_POLICY = "Password must contains combination of one digit from 0-9, one lowercase, one uppercase characters and one special symbols in the list \"@#$%\"";
	public static final String REG_EXPRESSION_PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	public static final String VALIDATE_USERNAME = "Username should not be null or empty";
	public static final String VALIDATE_USERNAME_SIZE = "Username should be minimum 6 and maximum 20 character";
	public static final String VALIDATE_GENDER = "Gender should be either MALE or FEMALE";
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private UserValidationMessages() {
	}
}
