package pl.mpas.parent_children.application;

import pl.mpas.parent_children.config.Configuration;
import pl.mpas.parent_children.dao.PersonDao;
import pl.mpas.parent_children.dao.PersonDaoImpl;
import pl.mpas.parent_children.model.AdultOrChild;
import pl.mpas.parent_children.model.Person;
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

        Person peszko = new Person("Sławek", "Peszko", 45, AdultOrChild.ADULT);
        Person peszkowa = new Person("Helena", "Peszko", 18, AdultOrChild.ADULT);

        Person robert = new Person("Robert", "L.", 30, AdultOrChild.ADULT);
        Person ania = new Person("Ania", "L.", 30, AdultOrChild.ADULT);

        Person klara = new Person("Klara", "L", 1, AdultOrChild.CHILD);
        Person jurek = new Person("Jurek", "L.", 0, AdultOrChild.CHILD);
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
