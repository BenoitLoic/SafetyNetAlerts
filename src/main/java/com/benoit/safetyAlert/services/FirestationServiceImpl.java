package com.benoit.safetyAlert.services;

import com.benoit.safetyAlert.dao.FirestationDao;
import com.benoit.safetyAlert.dto.PersonInfo;
import com.benoit.safetyAlert.exceptions.DataAlreadyExistException;
import com.benoit.safetyAlert.exceptions.DataNotFindException;
import com.benoit.safetyAlert.model.Firestation;
import com.benoit.safetyAlert.model.Persons;
import com.benoit.safetyAlert.repository.DataRepository;
import com.benoit.safetyAlert.utility.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/** This class contains some method to extract fire station data from DataRepository. */
@Service
public class FirestationServiceImpl implements FirestationService {

  /** The Data repository. */
  @Autowired private DataRepository dataRepository;

  @Autowired private MedicalRecordsService medicalRecordsService;
  @Autowired private FirestationDao firestationDao;


  @Override
  public Collection<String> getPhoneNumber(String station) {
    List<String> fireStationAddress = getFirestationAddress(station);
    Collection<String> phoneNumber =
        new HashSet<>(); // creation d'un hashset pour éviter les doublons
    for (String address : fireStationAddress) {
      List<Persons> personByAddress = dataRepository.getPersonByAddress(address);
      for (Persons person : personByAddress) {
        phoneNumber.add(person.getPhone());
      }
    }
    return phoneNumber;
  }

  @Override
  public Collection<Object> getPersonCoveredByFireStation(String stationNumber) {

    Counter counter = new Counter();
    Collection<Object> personCovered = new ArrayList<>();
    for (String address : getFirestationAddress(stationNumber)) {
      List<Persons> personByAddress = dataRepository.getPersonByAddress(address);
      for (Persons person : personByAddress) {
        List<String> user = new ArrayList<>();
        PersonInfo tmp =
            medicalRecordsService.getFullPersonInfo(person.getFirstName(), person.getLastName());
        user.add(tmp.getFirstName());
        user.add(tmp.getLastName());
        user.add(tmp.getAddress());
        user.add(tmp.getPhone());
        personCovered.add(user);

        counter.process(tmp.getAge());
      }
    }

    personCovered.add(counter.getAll());
    counter.reset();

    return personCovered;
  }

  @Override
  public Collection<Object> getFloodStations(List<String> station) {

    Collection<Object> floodStations = new ArrayList<>();

    for (String address : getFirestationAddress(station)) {

      Collection<Object> floodStationsAddress = new ArrayList<>();

      for (Persons person : dataRepository.getPersonByAddress(address)) {

        Collection<String> personInfos = new ArrayList<>();
        PersonInfo tmpPersonInfo =
            medicalRecordsService.getFullPersonInfo(person.getFirstName(), person.getLastName());

        Collections.addAll(
            personInfos,
            tmpPersonInfo.getFirstName(),
            tmpPersonInfo.getLastName(),
            "Medication: " + tmpPersonInfo.getMedication(),
            "Allergies: " + tmpPersonInfo.getAllergies(),
            String.valueOf(tmpPersonInfo.getAge()),
            tmpPersonInfo.getPhone());

        floodStationsAddress.add(personInfos);
      }

      Collections.addAll(floodStations, address, floodStationsAddress);
    }

    return floodStations;
  }

  @Override
  public boolean createFirestation(Firestation firestation) {

    if (!dataRepository.getDatabaseJson().getFirestations().contains(firestation)) {

      firestationDao.createFirestation(firestation);
      return true;
    } else {
      throw new DataAlreadyExistException(
          "this firestation "
              + firestation.getStation()
              + " / address : "
              + firestation.getAddress()
              + " already exist");
    }
  }

  @Override
  public boolean deleteFirestation(Firestation firestation) {

    if (dataRepository.getDatabaseJson().getFirestations().contains(firestation)) {

      firestationDao.deleteFirestation(firestation);
      return true;
    } else {
      throw new DataNotFindException(
          "this firestation "
              + firestation.getStation()
              + "/ address : "
              + firestation.getAddress()
              + " doesn't exist.");
    }
  }
}
