package com.openmind.springjwt.springbootjwt.services;

import com.openmind.springjwt.springbootjwt.domain.User;
import com.openmind.springjwt.springbootjwt.domain.Users;

public interface UserService {

  public abstract Users findAll();

  public abstract User createUser(User user);


}
