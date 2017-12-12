package com.openretails.data;

public class Single<T> {

	private T data;

	public Single() {
		super();
	}

	public Single(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
