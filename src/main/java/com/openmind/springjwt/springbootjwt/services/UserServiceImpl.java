package com.openmind.springjwt.springbootjwt.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.openmind.springjwt.springbootjwt.domain.User;
import com.openmind.springjwt.springbootjwt.domain.Users;
import com.openmind.springjwt.springbootjwt.jpa.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  private BCryptPasswordEncoder encoder;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Autowired
  @Qualifier("bcryptPasswordEncoder")
  public void setEncoder(BCryptPasswordEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public Users findAll() {

    final List<User> allUsers = new ArrayList<>();

    userRepository.findAll().forEach(entity -> {

      User user = new User();
      user.setId(entity.getId());
      user.setUserName(entity.getName());
      user.setPassword(entity.getPassword());
      user.setAuthorities(entity.getAuthorities());
      allUsers.add(user);
    });

    Users allSystemUsers = new Users();
    allSystemUsers.setUsers(allUsers);
    return allSystemUsers;
  }

  @Override
  public User createUser(User user) {

    com.openmind.springjwt.springbootjwt.jpa.entities.User entity =
        new com.openmind.springjwt.springbootjwt.jpa.entities.User();

    entity.setName(user.getUserName());
    entity.setPassword(encoder.encode(user.getPassword()));
    entity.setAuthorities(user.getAuthorities());

    entity = userRepository.save(entity);

    user.setId(entity.getId());
    user.setUserName(entity.getName());
    user.setPassword("");
    user.setAuthorities(entity.getAuthorities());

    return user;
  }

}
