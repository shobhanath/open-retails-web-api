package com.openretails.common.exception.format;

import java.util.List;

import lombok.Data;

@Data
public class ExceptionMessage {
	private int statusCode;
	private List<String> errorMessages;
	private String resource;
	private String errorCode;
	private String errorType;
}
