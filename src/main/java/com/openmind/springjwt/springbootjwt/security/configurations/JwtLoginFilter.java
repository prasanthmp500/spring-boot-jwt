package com.openmind.springjwt.springbootjwt.security.configurations;

import static com.openmind.springjwt.springbootjwt.security.utils.SecurityConstants.EXPIRATION_TIME;
import static com.openmind.springjwt.springbootjwt.security.utils.SecurityConstants.HEADER_STRING;
import static com.openmind.springjwt.springbootjwt.security.utils.SecurityConstants.SECRET;
import static com.openmind.springjwt.springbootjwt.security.utils.SecurityConstants.TOKEN_PREFIX;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

  private AuthenticationManager authenticationManager;

  protected JwtLoginFilter(AuthenticationManager authenticationManager,
      RequestMatcher requiresAuthenticationRequestMatcher) {
    super(requiresAuthenticationRequestMatcher);
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

    BufferedReader br = request.getReader();

    StringBuilder sbf = new StringBuilder();
    String line = null;
    while ((line = br.readLine()) != null) {
      sbf.append(line);
    }
    ObjectMapper objectMapper = new ObjectMapper();

    objectMapper.readValue(sbf.toString().getBytes(),
        com.openmind.springjwt.springbootjwt.domain.User.class);

    com.openmind.springjwt.springbootjwt.domain.User user = objectMapper.readValue(
        sbf.toString().getBytes(), com.openmind.springjwt.springbootjwt.domain.User.class);

    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        user.getUserName(), user.getPassword(), Collections.emptyList()));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {

    String jwtToken = Jwts.builder().setSubject(((User) authResult.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET).compact();

    response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);

  }

}
