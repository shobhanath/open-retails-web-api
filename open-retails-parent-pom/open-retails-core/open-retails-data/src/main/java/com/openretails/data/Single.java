package com.openretails.data;

import javax.validation.Valid;

public class Single<T> {

	@Valid
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
