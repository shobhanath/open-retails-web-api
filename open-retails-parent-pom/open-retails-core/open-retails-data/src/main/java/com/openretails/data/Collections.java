package com.openretails.data;

import java.util.Collection;

public class Collections<T> {

	private Collection<T> collections;

	public Collections() {
		super();
	}

	public Collections(Collection<T> collections) {
		super();
		this.collections = collections;
	}

	public Collection<T> getCollectionObj() {
		return collections;
	}

	public void setCollectionObj(Collection<T> collections) {
		this.collections = collections;
	}
}
