package com.openmind.springjwt.springbootjwt.security.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.openmind.springjwt.springbootjwt.jpa.entities.User;
import com.openmind.springjwt.springbootjwt.jpa.repository.UserRepository;

@Component
public class SpringBootJWTUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByName(userName);

		if (user == null) {
			return null;
		}

		UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(userName);
		String authorities = user.getAuthorities();

		List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();

		for (String authority : authorities.split(",")) {
			authoritiesList.add(new SimpleGrantedAuthority(authority));
		}

		userBuilder.password(user.getPassword());
		userBuilder.authorities(authorities);
		return userBuilder.build();
	}

}
