package com.openmind.springjwt.springbootjwt.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
