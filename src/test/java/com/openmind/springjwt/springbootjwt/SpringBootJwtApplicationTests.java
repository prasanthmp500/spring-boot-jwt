package com.openmind.springjwt.springbootjwt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.openmind.springjwt.springbootjwt.jpa.entities.Pet;
import com.openmind.springjwt.springbootjwt.jpa.repository.PetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootJwtApplication.class})
@DataJpaTest
public class SpringBootJwtApplicationTests {


  @MockBean
  private PetRepository petRepository;

  @Before
  public void setup() {
    Pet petcat = new Pet(1, "popuet", "cat");
    Pet petdog = new Pet(2, "bruno", "dog");
    petRepository.save(petcat);
    petRepository.save(petdog);

  }

  @Test
  public void contextLoads() {}

}
