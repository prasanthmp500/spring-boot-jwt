package com.openmind.springjwt.springbootjwt.security.configurations;

import static com.openmind.springjwt.springbootjwt.security.utils.SecurityConstants.HEADER_STRING;
import static com.openmind.springjwt.springbootjwt.security.utils.SecurityConstants.SECRET;
import static com.openmind.springjwt.springbootjwt.security.utils.SecurityConstants.TOKEN_PREFIX;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openmind.springjwt.springbootjwt.rest.errors.Error;
import io.jsonwebtoken.Jwts;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private ObjectMapper objectMapper = new ObjectMapper();

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    try {

      String header = request.getHeader(HEADER_STRING);

      if (header == null || !header.startsWith(TOKEN_PREFIX)) {
        chain.doFilter(request, response);
        return;
      }

      String user = Jwts.parser().setSigningKey(SECRET)
          .parseClaimsJws(header.replaceAll(TOKEN_PREFIX, "")).getBody().getSubject();

      if (user != null) {
        UsernamePasswordAuthenticationToken upat =
            new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(upat);

      } else {
        SecurityContextHolder.getContext().setAuthentication(null);
      }

    } catch (Exception e) {

      SecurityContextHolder.clearContext();
      com.openmind.springjwt.springbootjwt.rest.errors.Error error =
          new Error(response.SC_INTERNAL_SERVER_ERROR, e.getMessage());
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getOutputStream().println(objectMapper.writeValueAsString(error));

      return;
    }

    chain.doFilter(request, response);
  }

}
