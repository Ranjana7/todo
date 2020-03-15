package com.test.deloitte.handler.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.deloitte.api.model.ErrorResponse;
import com.test.deloitte.enums.ErrorCodes;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

  @ExceptionHandler(value = { InvalidDataAccessApiUsageException.class,ConstraintViolationException.class })
  public ResponseEntity<ErrorResponse> handleDataAccessApiUsageExceptions(InvalidDataAccessApiUsageException e, HttpServletRequest request) {
    log.error(String.format("InvalidDataAccessApiUsageException.handleDataAccessApiUsageExceptions() :[%s] ", e.getMessage()));
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(String.valueOf(ErrorCodes.INVALID_INPUT.getCode()));
    errorResponse.setMessage(e.getMessage());
    errorResponse.setPath(request.getServletPath());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = { TodoServiceException.class })
  public ResponseEntity<ErrorResponse> handleTodoServiceException(TodoServiceException e, HttpServletRequest request) {
    log.error(String.format("errorMessage=[%s]", e.getMessage()));
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(String.valueOf(ErrorCodes.INVALID_INPUT.getCode()));
    errorResponse.setMessage(e.getMessage());
    errorResponse.setPath(request.getServletPath());

    return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(value = { Exception.class })
  public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
    log.error(String.format(e.getMessage(), e));
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(String.valueOf(ErrorCodes.INVALID_INPUT.getCode()));
    errorResponse.setMessage(e.getMessage());
    errorResponse.setPath(request.getServletPath());

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
