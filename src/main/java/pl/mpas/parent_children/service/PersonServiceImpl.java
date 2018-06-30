package pl.mpas.parent_children.service;

import pl.mpas.parent_children.dao.PersonDao;
import pl.mpas.parent_children.model.Person;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean savePerson(Person person) {
        return false;
    }

    @Override
    public boolean deletePerson(Person person) {
        return false;
    }

    @Override
    public boolean updatePerson(Person person) {
        return false;
    }

    @Override
    public List<Person> getAllPersons() {
        return null;
    }

    @Override
    public List<Person> getPersonsWithoutChildren() {
        return null;
    }

    @Override
    public List<Person> getOnlyPersonsWithChildren() {
        return null;
    }
}
