package pl.mpas.parent_children.service;

import pl.mpas.parent_children.dao.PersonDao;
import pl.mpas.parent_children.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean savePerson(Person person) {
        System.out.println(String.format("Saving person:[%s]", person));

        return personDao.savePerson(person);
    }

    @Override
    public boolean deletePerson(Person person) {
        System.out.println(String.format("Deleting person: [%s]", person));

        return personDao.deletePerson(person);
    }

    @Override
    public boolean updatePerson(Person person) {
        System.out.println(String.format("Updating person: [%s]", person));

        return personDao.updatePerson(person);
    }

    @Override
    public List<Person> getAllPersons() {
        System.out.println("Getting all persons from dao");

        return personDao.getAllPersons();
    }

    @Override
    public List<Person> getPersonsWithoutChildren() {
        System.out.println("Getting persons without children");

        return personDao.getAllPersons().stream()
                .filter(person -> person.getChildren().isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getOnlyPersonsWithChildren() {
        System.out.println("Getting persons with children");

        return personDao.getAllPersons().stream()
                .filter(person -> !person.getChildren().isEmpty())
                .collect(Collectors.toList());
    }
}
