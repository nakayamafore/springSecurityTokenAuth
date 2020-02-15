package com.example.demo.exception;

import org.springframework.security.core.AuthenticationException;

public class WebApiAuthenticationException
    extends AuthenticationException {
  private static final long serialVersionUID = 1L;

  public WebApiAuthenticationException(final String msg) {
    super(msg);
  }

}
