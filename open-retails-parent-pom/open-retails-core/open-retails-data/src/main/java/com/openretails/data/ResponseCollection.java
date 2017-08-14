package com.openretails.data;

import java.util.Collection;

public class ResponseCollection<T> {

	private Collection<T> collectionResponse;

	public ResponseCollection(Collection<T> collectionResponse) {
		super();
		this.collectionResponse = collectionResponse;
	}

	public Collection<T> getCollectionResponse() {
		return collectionResponse;
	}

	public void setCollectionResponse(Collection<T> collectionResponse) {
		this.collectionResponse = collectionResponse;
	}
}
