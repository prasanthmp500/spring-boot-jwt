package com.openmind.springjwt.springbootjwt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;
import com.openmind.springjwt.springbootjwt.jpa.repository.PetRepository;

import ch.qos.logback.core.status.Status;

@Component
@Path("pets")
public class PetsInformationResource {

	@Autowired(required = true)
	private PetRepository petRepository;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response showAllPets() {

		try {

			final List<Pet> allPets = new ArrayList<Pet>();

			Iterable<Pet> pets = petRepository.findAll();

			pets.forEach((entity) -> {

				allPets.add(entity);

			});
			
			GenericEntity<List<Pet>> entity = new GenericEntity<List<Pet>>(allPets) {};
			return Response.ok(entity).build();
		} catch (Exception e) {
			return Response.serverError().status(Status.ERROR).build();
		}

	}

}
