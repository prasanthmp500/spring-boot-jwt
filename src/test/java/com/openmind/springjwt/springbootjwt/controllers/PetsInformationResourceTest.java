package com.openmind.springjwt.springbootjwt.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;
import com.openmind.springjwt.springbootjwt.services.PetService;

/**
 * 
 * @author prasanth
 * used @WithMockUser to avoid spring security 401 exception thrown 
 * also used hamcrest library.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PetsInformationResource.class)
@WithMockUser
public class PetsInformationResourceTest {

	@MockBean
	private PetService petService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void showAllPets_When_ThereArePets_ThenReturnList() throws Exception {

		List<Pet> pets = new ArrayList<>();

		pets.add(new Pet(1, "doudou", "cat"));
		pets.add(new Pet(2, "bruno", "dog"));

		when(petService.findAllPets()).thenReturn(pets);

		this.mvc.perform(get("/pets").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$.[0].id", equalTo(1)))
				.andExpect(jsonPath("$.[0].name", equalTo("doudou")))
				.andExpect(jsonPath("$.[0].type", equalTo("cat")))
				.andExpect(jsonPath("$.[1].id", equalTo(2)))
				.andExpect(jsonPath("$.[1].name", equalTo("bruno")))
				.andExpect(jsonPath("$.[1].type", equalTo("dog")));
		
		verify(petService).findAllPets();
		
	}

}
