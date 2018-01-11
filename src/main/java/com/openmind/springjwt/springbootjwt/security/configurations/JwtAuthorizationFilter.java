package com.openmind.springjwt.springbootjwt.security.configurations;

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

import com.openmind.springjwt.springbootjwt.security.utils.SecurityConstants;

import io.jsonwebtoken.Jwts;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
		
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
	      
		String user = Jwts.parser().setSigningKey(SecurityConstants.SECRET).
		parseClaimsJws( header.replaceAll(SecurityConstants.TOKEN_PREFIX, "")).
		getBody().
		getSubject();
		
		if(user!=null) {
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
			SecurityContextHolder.getContext().setAuthentication(upat);
			
		} else {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		
		}catch(Exception e ) {
			
			SecurityContextHolder.clearContext();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			return;
		}
		
		
		
		chain.doFilter(request, response);
	}
	

}
