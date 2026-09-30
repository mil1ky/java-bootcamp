package com.northstar.crm.api;

import com.northstar.crm.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(
          MethodArgumentNotValidException ex,
          WebRequest request) {

    String correlationId = getCorrelationId(request);

    List<ErrorResponse.FieldViolation> violations =
            ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> new ErrorResponse.FieldViolation(
                            error.getField(),
                            error.getDefaultMessage()))
                    .toList();

    ErrorResponse response = new ErrorResponse(
);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(
          IllegalArgumentException ex,
          WebRequest request) {

    ErrorResponse response = new ErrorResponse();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ErrorResponse> handleConflict(
          IllegalStateException ex,
          WebRequest request) {

    ErrorResponse response = new ErrorResponse( );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleSafe500(
          Exception ex,
          WebRequest request) {

    ErrorResponse response = new ErrorResponse( );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  private String getCorrelationId(WebRequest request) {
    String correlationId = request.getHeader("X-Correlation-Id");
    return correlationId != null && !correlationId.isBlank()
            ? correlationId
            : "lab-request-001";
  }
}