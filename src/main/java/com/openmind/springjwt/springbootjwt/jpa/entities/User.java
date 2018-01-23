package com.openmind.springjwt.springbootjwt.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "USERNAME", nullable = false, length = 100)
  private String name;

  @Column(name = "PASSWORD", nullable = false, length = 150)
  private String password;

  @Column(name = "AUTHORITIES", nullable = false, length = 100)
  private String authorities;

  @Column(name = "ENABLED", nullable = false, length = 1)
  private boolean active;

  public User() {}

  public User(String name, String password, String authorities, boolean active) {
    super();
    this.name = name;
    this.password = password;
    this.authorities = authorities;
    this.active = active;
  }

  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
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


  public boolean isActive() {
    return active;
  }


  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", password=" + password + ", authorities="
        + authorities + ", active=" + active + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (id != other.id)
      return false;
    return true;
  }

}
