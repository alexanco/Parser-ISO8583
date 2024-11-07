package com.alexanco.api;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private HttpStatus statusCode = HttpStatus.BAD_REQUEST;

  public AppException(String message) {
    super(message);
  }

  public AppException(String message, HttpStatus statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }
}
