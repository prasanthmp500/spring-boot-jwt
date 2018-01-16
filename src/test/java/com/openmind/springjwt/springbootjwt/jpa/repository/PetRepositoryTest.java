package com.openmind.springjwt.springbootjwt.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PetRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private PetRepository petRepository;

	@Before
	public void init() {

		Pet p = new Pet("doudou", "cat");
		testEntityManager.persist(p);
		p = new Pet("bruno", "dog");
		testEntityManager.persist(p);
	}

	@Test
	public void testFindAllPets() {

		Iterable<Pet> iterable = petRepository.findAll();
		List<Pet> pets = new ArrayList<>();
		iterable.forEach((e) -> {
			pets.add(e);
		});
		assertThat(pets.size() == 2);
	}

}
