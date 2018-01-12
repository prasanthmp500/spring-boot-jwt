package com.openmind.springjwt.springbootjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openmind.springjwt.springbootjwt.domain.User;
import com.openmind.springjwt.springbootjwt.domain.Users;
import com.openmind.springjwt.springbootjwt.services.UserService;


@RestController
@RequestMapping("users")
public class UserControllerResource {

	@Autowired(required = true)
	private UserService userService;


	@GetMapping(produces= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> showAllUsers() {

		try {
			return new ResponseEntity<Users>(userService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path="/create",consumes= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
			produces= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public  ResponseEntity<?> createUser(@RequestBody User user) {
		
		try {
			user = userService.createUser(user);
			
			return new ResponseEntity<User>(user, HttpStatus.OK);

		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path="/login",consumes= {  MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
			produces= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> loginUser(@RequestBody User user) {

		try {
			return new ResponseEntity<>("login success", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
