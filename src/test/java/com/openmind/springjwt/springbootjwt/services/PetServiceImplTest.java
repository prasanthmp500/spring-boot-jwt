package com.openmind.springjwt.springbootjwt.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;
import com.openmind.springjwt.springbootjwt.jpa.repository.PetRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {PetServiceImpl.class})
public class PetServiceImplTest {

	@MockBean
	private PetRepository petRepository;

	@Autowired
	private PetService petService;
	
	@Test
	public void findAllPetsWhenResultIsSuccessShouldListOfPets() {

		List<Pet> pets = new ArrayList<>();
		Pet p1 = new Pet(1, "doudou", "cat");
		Pet p2 = new Pet(2, "bruno", "aligator");

		pets.add(p1);
		pets.add(p2);
		when(petRepository.findAll()).thenReturn(pets);

		List<Pet> allPets = petService.findAllPets();
		assertThat(allPets.size() == 2);
		verify(petRepository).findAll();

	}

	@Test
	public void findAllPetsWhenThereIsNoPetsShouldEmptyList() {

		List<Pet> pets = new ArrayList<>();
		
		when(petRepository.findAll()).thenReturn(pets);

		List<Pet> allPets = petService.findAllPets();
		assertThat(allPets != null);
		assertThat(allPets.size() == 0);

		verify(petRepository).findAll();

	}

	
	
}
