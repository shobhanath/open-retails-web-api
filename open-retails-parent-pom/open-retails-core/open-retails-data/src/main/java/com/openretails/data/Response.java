package com.openretails.data;

import java.io.Serializable;


public class Response implements Serializable {

	private static final long serialVersionUID = -4852716679642945678L;

	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "[response=" + response + "]";
	}
}
