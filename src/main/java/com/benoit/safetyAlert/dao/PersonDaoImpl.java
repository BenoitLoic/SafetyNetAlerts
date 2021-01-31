package com.benoit.safetyAlert.dao;

import com.benoit.safetyAlert.model.Persons;
import com.benoit.safetyAlert.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDaoImpl implements PersonDao {

    private final DataRepository dataRepository;

    @Autowired
    public PersonDaoImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public boolean createPerson(Persons person) {

        // ajout de la nouvelle personne en mémoire
        dataRepository.getPersons().add(person);
        // commit pour appliquer les changement sur le json
        dataRepository.commit();
        return true;
    }

    @Override
    public boolean deletePerson(Persons person) {
    dataRepository.getPersons().remove(person);
    dataRepository.commit();
    return true;
  }

  @Override
  public boolean updatePerson(Persons person) {
    return false;
  }
}
