package com.openmind.springjwt.springbootjwt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openmind.springjwt.springbootjwt.domain.User;
import com.openmind.springjwt.springbootjwt.domain.Users;
import com.openmind.springjwt.springbootjwt.jpa.repository.UserRepository;


@RestController
@RequestMapping("users")
public class UserControllerResource {

	@Autowired(required = true)
	private UserRepository userRepository;

	@Autowired
	@Qualifier("bcrypPasswordEncoder")
	private BCryptPasswordEncoder encoder;

	@GetMapping(produces= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> showAllUsers() {

		try {

			final List<User> allUsers = new ArrayList<User>();
			Iterable<com.openmind.springjwt.springbootjwt.jpa.entities.User> users = userRepository.findAll();
			users.forEach((entity) -> {

				User user = new User();
				user.setId(entity.getId());
				user.setUserName(entity.getName());
				user.setPassword(entity.getPassword());
				user.setAuthorities(entity.getAuthorities());
				allUsers.add(user);
			});
			
			Users allSystemUsers =  new Users();
			allSystemUsers.setUsers(allUsers);
			return new ResponseEntity<Users>(allSystemUsers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path="/create",consumes= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
			produces= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public  ResponseEntity<?> createUser(User user) {
		
		com.openmind.springjwt.springbootjwt.jpa.entities.User entity = new com.openmind.springjwt.springbootjwt.jpa.entities.User();
		entity.setName(user.getUserName());
		entity.setPassword(encoder.encode(user.getPassword()));
		entity.setAuthorities(user.getAuthorities());
		try {

			com.openmind.springjwt.springbootjwt.jpa.entities.User newUser = userRepository.save(entity);

			user.setId(newUser.getId());
			user.setUserName(newUser.getName());
			user.setPassword("");
			user.setAuthorities(newUser.getAuthorities());
			
			return new ResponseEntity<User>(user, HttpStatus.OK);

		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path="/login",consumes= {  MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
			produces= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> loginUser(User user) {

		try {
			return new ResponseEntity<>("login success", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
