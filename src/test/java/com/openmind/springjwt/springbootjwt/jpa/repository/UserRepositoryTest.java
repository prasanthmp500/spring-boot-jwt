package com.openmind.springjwt.springbootjwt.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.openmind.springjwt.springbootjwt.jpa.entities.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void init() {
		testEntityManager.persist(new User("testuser", "testuser", "IS_ADMIN", true));
	}
	
	@Test
	public void testFindByName() {
		
		User user = userRepository.findByName("testuser");
		assertThat(user).isNotNull();
		
	}
	
	
}
