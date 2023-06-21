package com.tajohnson.authentication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
  @GeneratedValue(strategy = GenerationType.UUID)
  @Id
  private UUID id;

  @NotBlank(message = "Username is required!")
  @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
  private String userName;

  @Column(unique = true)
  @NotBlank(message = "Email is required!")
  @Email(message = "Please enter a valid email")
  private String email;

  @NotBlank(message = "Password is required!")
  @Size(message = "Password must be between 8 and 128 characters")
  private String password;

  @Transient
  @NotBlank(message = "Password confirmation is required!")
  @Size(min = 8, max = 128, message = "Passwords must match")
  private String confirmPassword;


  // *** createdAt AND updatedAt FIELDS NOT NEEDED FOR USER AUTHENTICATION ***

  public User() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}