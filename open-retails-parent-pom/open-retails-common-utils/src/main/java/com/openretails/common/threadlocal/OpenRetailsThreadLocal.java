package com.openretails.common.threadlocal;

public class OpenRetailsThreadLocal {

	public static final ThreadLocal<ThreadLocalData> userThreadLocal = new ThreadLocal<>();

	public static ThreadLocalData get() {
		return userThreadLocal.get();
	}

	public static void set(ThreadLocalData user) {
		userThreadLocal.set(user);
	}

	public static void unset() {
		userThreadLocal.remove();
	}

	private OpenRetailsThreadLocal() {
	}

}
