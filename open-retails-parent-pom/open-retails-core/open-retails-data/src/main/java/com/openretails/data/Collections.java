package com.openretails.data;

import java.util.Collection;

import javax.validation.Valid;

public class Collections<T> {

	@Valid
	private Collection<T> collection;

	public Collections() {
		super();
	}

	public Collections(Collection<T> collection) {
		super();
		this.collection = collection;
	}

	public Collection<T> getCollection() {
		return collection;
	}

	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}
}
