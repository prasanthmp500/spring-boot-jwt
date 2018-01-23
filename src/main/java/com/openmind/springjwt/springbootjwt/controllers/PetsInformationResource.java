package com.openmind.springjwt.springbootjwt.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;
import com.openmind.springjwt.springbootjwt.services.PetService;

@RestController
public class PetsInformationResource {

  @Autowired(required = true)
  private PetService petService;

  @GetMapping(path = "/pets",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> showAllPets() {
    try {
      return new ResponseEntity<List<Pet>>(petService.findAllPets(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
