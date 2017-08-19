package com.openretails.web.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.openretails.common.exception.OpenRetailsBusinessException;
import com.openretails.common.exception.OpenRetailsDataAccessException;
import com.openretails.common.exception.OpenRetailsException;
import com.openretails.common.exception.OpenRetailsForbiddenException;
import com.openretails.common.exception.OpenRetailsNotAuthorizedException;
import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.common.exception.format.ExceptionMessage;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private HttpServletRequest servletRequest;

	@ExceptionHandler({ OpenRetailsBusinessException.class, OpenRetailsValidationException.class,
			OpenRetailsNotAuthorizedException.class, OpenRetailsNotAuthorizedException.class,
			OpenRetailsDataAccessException.class, OpenRetailsRuntimeException.class, OpenRetailsException.class,
			Exception.class })
	public ResponseEntity<ExceptionMessage> handleCustomException(Exception ex, WebRequest request) {
		final ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setErrorMessage(ex.getMessage());
		exceptionMessage.setResource(servletRequest.getRequestURI());
		exceptionMessage.setErrorType(ex.getClass().getName());
		if (ex instanceof OpenRetailsValidationException) {
			exceptionMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
			exceptionMessage.setErrorCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
			return new ResponseEntity<ExceptionMessage>(exceptionMessage, HttpStatus.BAD_REQUEST);
		} else if (ex instanceof OpenRetailsNotAuthorizedException) {
			exceptionMessage.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			exceptionMessage.setErrorCode(HttpStatus.UNAUTHORIZED.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.UNAUTHORIZED);
		} else if (ex instanceof OpenRetailsForbiddenException) {
			exceptionMessage.setStatusCode(HttpStatus.FORBIDDEN.value());
			exceptionMessage.setErrorCode(HttpStatus.FORBIDDEN.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.FORBIDDEN);
		} else if (ex instanceof OpenRetailsBusinessException) {
			exceptionMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
			exceptionMessage.setErrorCode(HttpStatus.NOT_FOUND.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
		} else if (ex instanceof OpenRetailsDataAccessException) {
			exceptionMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
			exceptionMessage.setErrorCode(HttpStatus.NOT_FOUND.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
		} else if (ex instanceof OpenRetailsRuntimeException) {
			exceptionMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
			exceptionMessage.setErrorCode(HttpStatus.NOT_FOUND.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
		} else if (ex instanceof OpenRetailsException) {
			exceptionMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
			exceptionMessage.setErrorCode(HttpStatus.NOT_FOUND.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
		} else {
			exceptionMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			exceptionMessage.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
