package com.openmind.springjwt.springbootjwt.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class User {

  public User() {}

  public User(String userName, String password, String authorities) {
    super();
    this.userName = userName;
    this.password = password;
    this.authorities = authorities;
  }

  public User(int id, String userName, String password, String authorities) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.authorities = authorities;
  }

  @JsonProperty("user-id")
  private int id;

  @JsonProperty("user-name")
  private String userName;

  @JsonProperty("user-password")
  private String password;

  @JsonProperty("user-authorities")
  private String authorities;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAuthorities() {
    return authorities;
  }

  public void setAuthorities(String authorities) {
    this.authorities = authorities;
  }


}
