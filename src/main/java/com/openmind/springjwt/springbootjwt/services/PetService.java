package com.openmind.springjwt.springbootjwt.services;

import java.util.List;
import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;

public interface PetService {

  public List<Pet> findAllPets();

}
