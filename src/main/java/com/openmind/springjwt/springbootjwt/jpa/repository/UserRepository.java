package com.openmind.springjwt.springbootjwt.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import com.openmind.springjwt.springbootjwt.jpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

  public User findByName(String name);


}
