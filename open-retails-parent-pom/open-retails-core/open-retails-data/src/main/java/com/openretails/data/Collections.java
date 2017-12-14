package com.openretails.data;

import java.util.Collection;

public class Collections<T> {

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
