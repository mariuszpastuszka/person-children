package pl.mpas.parent_children.application;

import pl.mpas.parent_children.config.Configuration;
import pl.mpas.parent_children.dao.PersonDao;
import pl.mpas.parent_children.dao.PersonDaoImpl;
import pl.mpas.parent_children.model.AdultOrChild;
import pl.mpas.parent_children.model.Person;
import pl.mpas.parent_children.model.Sex;
import pl.mpas.parent_children.service.PersonService;
import pl.mpas.parent_children.service.PersonServiceImpl;
import pl.mpas.parent_children.service.WelcomeService;

import java.sql.Connection;

public class ConsoleApplication {

    public static void main(String[] args) {
        Connection dbConnection = Configuration.getInstance().getDbConnection();
        PersonDao databaseDao = new PersonDaoImpl(dbConnection);
        PersonService personService = new PersonServiceImpl(databaseDao);

        WelcomeService welcomeService = new WelcomeService(WelcomeService.EN_LANG);
        welcomeService.sayHello();

        Person peszko = new Person("Sławek", "Peszko", 45, AdultOrChild.ADULT, Sex.MALE);
        Person peszkowa = new Person("Helena", "Peszko", 18, AdultOrChild.ADULT, Sex.FEMALE);

        Person robert = new Person("Robert", "L.", 30, AdultOrChild.ADULT, Sex.MALE);
        Person ania = new Person("Ania", "L.", 30, AdultOrChild.ADULT, Sex.FEMALE);

        Person klara = new Person("Klara", "L", 1, AdultOrChild.CHILD, Sex.FEMALE);
        Person jurek = new Person("Jurek", "L.", 0, AdultOrChild.CHILD, Sex.MALE);
        robert.addChild(klara);
        robert.addChild(jurek);
        ania.addChild(klara);
        ania.addChild(jurek);

        System.out.println("Trying to save persons to database");

        personService.savePerson(peszko);
        personService.savePerson(peszkowa);
        personService.savePerson(ania);
        personService.savePerson(robert);

        System.out.println("Reading persons from db");
        System.out.println(personService.getAllPersons());
    }
}
