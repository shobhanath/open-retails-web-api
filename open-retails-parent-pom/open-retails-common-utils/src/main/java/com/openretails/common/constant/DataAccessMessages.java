package com.openretails.common.constant;

public final class DataAccessMessages {

	public static final String VALIDATE_NAME = "Name should not be null or empty";
	public static final String VALIDATE_NAME_SIZE = "Name must be between 2 and 255 characters";

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

	/* OfferType */
	public static final String VALIDATE_MIN_DISCOUNT_PERCENTAGE = "Minimum discount should be zero";

	/* Offer */
	public static final String VALIDATE_OFFER_TYPE_NOT_NULL = "Offer type should not be null";
	public static final String VALIDATE_PRODUCT_NOT_NULL = "Product should not be null";

	/* Product */
	public static final String VALIDATE_PRODUCT_CAT_NOT_NULL = "Product catetory should not be null";

	/* ProductCategory */
	public static final String VALIDATE_MIN_GST_PERCENTAGE = "Minimum gst should be zero";
	public static final String FAILED_CREATE_PROD_CATEGORY = "Failed to create product category : ";
	public static final String FAILED_TO_ENABLE_PROD_CATEGORY = "Failed to enable product category";
	public static final String FAILED_TO_DISABLE_PROD_CATEGORY = "Failed to disable product category";
	public static final String PROD_CATEGORY_NOT_FOUND = "Product category not found";
	public static final String PROD_CATEGORY_BY_NAME_NOT_FOUND = "Product category not found with the given details";
	public static final String ID_BY_NAME_NOT_FOUND = "Identity not found with the given details";
	public static final String NAME_BY_ID_NOT_FOUND = "Name not found with the given details";
	public static final String SEARCH_BY_NAME_OR_ID_NOT_FOUND = "Product category not found by name or identity";
	/* Stock */
	public static final String VALIDATE_MIN_QTY = "Minimum quantity should be greater than zero";
	public static final String VALIDATE_MIN_BUY_PRICE = "Minimum buying price should be greater than zero";
	public static final String VALIDATE_MIN_MRP = "Minimum mrp should be greater than zero";

	private DataAccessMessages() {
	}
}
