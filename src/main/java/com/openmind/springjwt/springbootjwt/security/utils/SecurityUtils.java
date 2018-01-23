package com.openmind.springjwt.springbootjwt.security.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityUtils {

  public SecurityUtils() {}

  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder(16);
  }

}
