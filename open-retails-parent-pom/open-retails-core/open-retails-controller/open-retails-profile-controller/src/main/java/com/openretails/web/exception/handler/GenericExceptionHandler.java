package com.openretails.web.exception.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.openretails.common.exception.OpenRetailsBusinessException;
import com.openretails.common.exception.OpenRetailsDataAccessException;
import com.openretails.common.exception.OpenRetailsException;
import com.openretails.common.exception.OpenRetailsForbiddenException;
import com.openretails.common.exception.OpenRetailsNotAuthorizedException;
import com.openretails.common.exception.OpenRetailsRuntimeException;
import com.openretails.common.exception.OpenRetailsValidationException;
import com.openretails.common.exception.format.ExceptionMessage;

@ControllerAdvice
public class GenericExceptionHandler {

	@Autowired
	private HttpServletRequest servletRequest;

	@ExceptionHandler({ OpenRetailsBusinessException.class, OpenRetailsValidationException.class,
			OpenRetailsNotAuthorizedException.class, OpenRetailsNotAuthorizedException.class,
			OpenRetailsDataAccessException.class, OpenRetailsRuntimeException.class, OpenRetailsException.class,
			Exception.class })
	public ResponseEntity<ExceptionMessage> handleCustomException(Exception ex, WebRequest request) {
		final ExceptionMessage exceptionMessage = new ExceptionMessage();
		exceptionMessage.setErrorMessages(new ArrayList<>(Arrays.asList(ex.getMessage())));
		exceptionMessage.setResource(servletRequest.getRequestURI());
		exceptionMessage.setErrorType(ex.getClass().getName());
		if (ex instanceof MethodArgumentNotValidException) {
			exceptionMessage.setErrorMessages(((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors()
					.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())
					);
			exceptionMessage.setErrorType(OpenRetailsValidationException.class.getName());
			exceptionMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
			exceptionMessage.setErrorCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
		} else if (ex instanceof OpenRetailsValidationException) {
			exceptionMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
			exceptionMessage.setErrorCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
		} else if (ex instanceof AccessDeniedException || ex instanceof OpenRetailsNotAuthorizedException) {
			exceptionMessage.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			exceptionMessage.setErrorCode(HttpStatus.UNAUTHORIZED.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.UNAUTHORIZED);
		} else if (ex instanceof OpenRetailsForbiddenException) {
			exceptionMessage.setStatusCode(HttpStatus.FORBIDDEN.value());
			exceptionMessage.setErrorCode(HttpStatus.FORBIDDEN.getReasonPhrase());
			return new ResponseEntity<>(exceptionMessage, HttpStatus.FORBIDDEN);
		} else if (ex instanceof OpenRetailsBusinessException || ex instanceof OpenRetailsDataAccessException
				|| ex instanceof OpenRetailsRuntimeException || ex instanceof OpenRetailsException) {
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
