package com.safetynet.safetyalerts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;
import javax.validation.constraints.NotBlank;

/**
 * Model for Persons.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Persons {
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  private String address;
  private String zip;
  private String city;
  private String phone;
  private String email;
  @JsonIgnore
  private Firestation firestation = new Firestation();
  @JsonIgnore
  private Medicalrecords medicalrecords = new Medicalrecords();

  public Persons() {
  }


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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Firestation getFirestation() {
    return firestation;
  }

  public void setFirestation(Firestation firestation) {
    this.firestation = firestation;
  }

  public Medicalrecords getMedicalrecords() {
    return medicalrecords;
  }

  public void setMedicalrecords(Medicalrecords medicalrecords) {
    this.medicalrecords = medicalrecords;
  }

  /**
   * Override on equals to take only firstName and lastName.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Persons persons = (Persons) o;
    return Objects.equals(firstName, persons.firstName)
        && Objects.equals(lastName, persons.lastName);
  }

  /**
   * Override on hash to take only firstName, lastName, phone and email.
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, phone, email);
  }

  @Override
  public String toString() {
    return "Persons{"
        + "firstName='"
        + firstName + '\''
        + ", lastName='"
        + lastName + '\''
        + ", address='"
        + address + '\''
        + ", zip='"
        + zip + '\''
        + ", city='"
        + city + '\''
        + ", phone='"
        + phone + '\''
        + ", email='"
        + email + '\''
        + ", firestation="
        + firestation
        + ", medicalrecords="
        + medicalrecords + '}';
  }
}
