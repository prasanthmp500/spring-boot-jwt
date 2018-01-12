package com.openmind.springjwt.springbootjwt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;
import com.openmind.springjwt.springbootjwt.jpa.repository.PetRepository;

@RestController
public class PetsInformationResource {

	@Autowired(required = true)
	private PetRepository petRepository;

	@GetMapping(path="/pets",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> showAllPets() {

		try {

			final List<Pet> allPets = new ArrayList<Pet>();

			Iterable<Pet> pets = petRepository.findAll();

			pets.forEach((entity) -> {

				allPets.add(entity);

			});
			
			return new ResponseEntity<List<Pet>>(allPets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
