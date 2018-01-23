package com.openmind.springjwt.springbootjwt.security.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class LoginUrlRequestMatcher implements RequestMatcher {

  private String uri;

  public LoginUrlRequestMatcher(String uri) {
    this.uri = uri;
  }

  @Override
  public boolean matches(HttpServletRequest request) {
    return request.getRequestURI().equals(uri);
  }

}
