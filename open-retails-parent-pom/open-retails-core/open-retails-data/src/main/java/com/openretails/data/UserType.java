package com.openretails.data;

public enum UserType {

	ADMIN(1), USER(2);

	private final int value;

	private UserType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
