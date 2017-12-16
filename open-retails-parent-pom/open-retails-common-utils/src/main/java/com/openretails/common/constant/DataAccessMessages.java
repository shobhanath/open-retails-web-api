package com.openretails.common.constant;

public final class DataAccessMessages {
	/* User */
	public static final String FAILED_CREATE_USERS = "Failed to create users : ";
	public static final String FAILED_UPDATE_USERS = "Failed to update users : ";
	public static final String FAILED_PARTIAL_UPDATE_USERS = "Failed to partial update users : ";
	public static final String USERS_NOT_FOUND = "User not found with the given user details";
	public static final String FAILED_TO_ENABLE_USERS = "Failed to enable users";
	public static final String FAILED_TO_DISABLE_USERS = "Failed to disable users";
	public static final String FAILED_TO_FETCH_ALL_USERS = "Failed to fetch all users";
	public static final String FAILED_TO_FETCH_USERS_BY_ID = "Failed to fetch user by id";
	public static final String FAILED_TO_FETCH_USERS_BY_USERNAME_OR_EMAIL = "Failed to fetch users by either username or primary email id";

	private DataAccessMessages() {
	}
}
