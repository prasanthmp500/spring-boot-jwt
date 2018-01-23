package com.openmind.springjwt.springbootjwt.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "allUsers")
public class Users {

  @XmlElement(name = "all-users")
  private List<User> users;

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }


}
