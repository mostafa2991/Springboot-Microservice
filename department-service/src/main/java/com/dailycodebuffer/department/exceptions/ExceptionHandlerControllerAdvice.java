package com.dailycodebuffer.department.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	private ExceptionResponse error;
	private String message;
	private String requestURI;
	private Map<String, Object> errorInfo;

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleException(final NotFoundException exception,
			final HttpServletRequest request) {

		error = new ExceptionResponse();
		message = exception.getMessage();
		requestURI = request.getRequestURI();

		error = setErrorProperties(error, message, requestURI);

		return error;
	}

	@ExceptionHandler(BadUrlException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionResponse handleException(final BadUrlException exception,
			final HttpServletRequest request) {

		error = new ExceptionResponse();
		message = exception.getMessage();
		requestURI = request.getRequestURI();

		error = setErrorProperties(error, message, requestURI);

		return error;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Map<String, Object>> unhandledPath(final NoHandlerFoundException e) {

		errorInfo = new LinkedHashMap<>();

		errorInfo.put("timestamp", new Date());
		errorInfo.put("httpCode", HttpStatus.NOT_FOUND.value());
		errorInfo.put("httpStatus", HttpStatus.NOT_FOUND.getReasonPhrase());
		errorInfo.put("errorMessage", e.getMessage());

		return new ResponseEntity<Map<String, Object>>(errorInfo, HttpStatus.NOT_FOUND);

	}

	private ExceptionResponse setErrorProperties(ExceptionResponse error, String message, String requestURI) {

		error.setErrorMessage(message);
		error.setRequestedURI(requestURI);
		error.setTimeStamp(new Date());

		return error;

	}

}
