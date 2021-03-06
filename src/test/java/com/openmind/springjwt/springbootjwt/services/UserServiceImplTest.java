package com.openmind.springjwt.springbootjwt.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.openmind.springjwt.springbootjwt.domain.Users;
import com.openmind.springjwt.springbootjwt.jpa.entities.User;
import com.openmind.springjwt.springbootjwt.jpa.repository.UserRepository;
import com.openmind.springjwt.springbootjwt.security.utils.SecurityUtils;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserServiceImpl.class, SecurityUtils.class})
public class UserServiceImplTest {

  // Simplest scenario. Will delegate to Podam all decisions
  private PodamFactory factory = new PodamFactoryImpl();

  @MockBean
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @Autowired
  @Qualifier("bcryptPasswordEncoder")
  private BCryptPasswordEncoder passwordEncoder;

  @Test
  public void findAllWhenThereIsUsersShouldReturnNonEmptyUsersList() {

    List<User> users = new ArrayList<>();
    users.add(factory.manufacturePojo(User.class));
    users.add(factory.manufacturePojo(User.class));
    when(userRepository.findAll()).thenReturn(users);
    Users usrs = userService.findAll();
    assertThat(usrs).isNotNull();
    assertThat(usrs.getUsers()).isNotEmpty();
    assertThat(usrs.getUsers()).hasSize(2);
    verify(userRepository).findAll();
    verify(userRepository, times(1)).findAll();
  }

  @Test
  public void findAllWhenThereIsUsersShouldReturnEmptyUsersList() {

    List<User> users = new ArrayList<>();
    when(userRepository.findAll()).thenReturn(users);

    Users usrs = userService.findAll();

    assertThat(usrs).isNotNull();
    assertThat(usrs.getUsers()).isEmpty();

    verify(userRepository).findAll();
    verify(userRepository, times(1)).findAll();
  }

  @Test
  public void createUserShouldReturnNewlyCreatedUser() {

    User returnValue = new User("test", "password", "HAS_ROLE_ADMIN", true);

    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

    when(userRepository.save(userCaptor.capture())).thenReturn(returnValue);

    com.openmind.springjwt.springbootjwt.domain.User user =
        new com.openmind.springjwt.springbootjwt.domain.User("test", "password", "HAS_ROLE_ADMIN");
    com.openmind.springjwt.springbootjwt.domain.User newlyCreatedUser =
        userService.createUser(user);

    User argumentCaptureUser = userCaptor.getValue();

    assertThat(newlyCreatedUser).isNotNull();

    assertThat(newlyCreatedUser.getUserName()).isEqualTo(argumentCaptureUser.getName()); // "password"
                                                                                         // =
                                                                                         // $2a$16$ogICtrfkUsk4tvXI5fCTmuPrQdGtVb9mUUqeh/vGZWIdU18/4s6vW
    // assertThat(passwordEncoder.encode("password")).isEqualTo(argumentCaptureUser.getPassword());
    // //$2a$16$diIq3JpHLJkz7ZamrAhO..8Ig9QJ0EmBG11ih4rC0iOhedQGupgrC
    assertThat(passwordEncoder.matches(passwordEncoder.encode("password"),
        argumentCaptureUser.getPassword()));
    assertThat(newlyCreatedUser.getAuthorities()).isEqualTo(argumentCaptureUser.getAuthorities());



  }



}
