package com.openretails.common.threadlocal;

public class ThreadLocalData {
	private Long loggedInUserId = null;

	public Long getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(Long loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
}
