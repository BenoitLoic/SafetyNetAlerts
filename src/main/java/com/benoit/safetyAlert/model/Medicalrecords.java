package com.benoit.safetyAlert.model;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Medicalrecords {

  @NotBlank private String firstName;
  @NotBlank private String lastName;
  @NotBlank private String birthdate;
  private List<String> medications = new ArrayList<>();
  private List<String> allergies = new ArrayList<>();

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public List<String> getMedications() {
    return medications;
  }

  public void setMedications(List<String> medications) {
    this.medications = medications;
  }

  public List<String> getAllergies() {
    return allergies;
  }

  public void setAllergies(List<String> allergies) {
    this.allergies = allergies;
  }
}
