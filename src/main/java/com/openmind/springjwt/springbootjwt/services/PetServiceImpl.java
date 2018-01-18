package com.openmind.springjwt.springbootjwt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;
import com.openmind.springjwt.springbootjwt.jpa.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {

	private PetRepository petRepository;

	@Autowired
	public void setPetRepository(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public List<Pet> findAllPets() {

		final List<Pet> allPets = new ArrayList<Pet>();
		petRepository.findAll().forEach((entity) -> {
			allPets.add(entity);
		});
		return allPets;
	}

}
