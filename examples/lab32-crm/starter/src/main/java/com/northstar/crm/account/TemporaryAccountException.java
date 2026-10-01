package com.northstar.crm.account;

public class TemporaryAccountException extends RuntimeException {
  public TemporaryAccountException(String message) {
    super(message);
  }
  public TemporaryAccountException(String message, Throwable cause) {
    super(message, cause);
  }
}
