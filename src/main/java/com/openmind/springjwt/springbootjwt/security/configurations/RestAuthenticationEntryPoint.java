package com.openmind.springjwt.springbootjwt.security.configurations;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openmind.springjwt.springbootjwt.rest.errors.Error;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    com.openmind.springjwt.springbootjwt.rest.errors.Error error =
        new Error(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getOutputStream().println(new ObjectMapper().writeValueAsString(error));

  }

}
