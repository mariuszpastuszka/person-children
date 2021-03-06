package pl.mpas.parent_children.dao;

import pl.mpas.parent_children.model.AdultOrChild;
import pl.mpas.parent_children.model.Person;
import pl.mpas.parent_children.model.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PersonDaoImpl implements PersonDao {
    private Connection dbConnection;

    public PersonDaoImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean savePerson(Person person) {
        if (person.getId() != Person.DEFAULT_ID) {
            return false;
        }
        // TODO: check if robert exits
        String insertPersonQuery = "INSERT INTO PERSON (NAME, SURNAME, AGE, ADULT_OR_CHILD, SEX) " +
                " VALUES (?, ?, ?, ?, ?)";
        String insertRelationschipQuery = "INSERT INTO \"PARENT-CHILD\" (PARENT_ID, CHILDREN_ID)" +
                "VALUES (?, ?)";
        PreparedStatement personStatement = null;
        PreparedStatement relashionshipStatement = null;
        try {
            personStatement = dbConnection.prepareStatement(insertPersonQuery);
            insertPerson(person, personStatement);

            for (Person child : person.getChildren()) {
                insertPerson(child, personStatement);
            }

            relashionshipStatement = dbConnection.prepareStatement(insertRelationschipQuery);
            insertPersonRelationship(person, person.getChildren(), relashionshipStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void insertPersonRelationship(Person parent, Set<Person> children, PreparedStatement ps) throws SQLException {
        for (Person child : children) {
            ps.setInt(1, parent.getId());
            ps.setInt(2, child.getId());
            int rowAffected = ps.executeUpdate();
            System.out.println("Number of added records: " + rowAffected);
        }
    }

    private void insertPerson(Person person, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getSurname());
        preparedStatement.setInt(3, person.getAge());
        preparedStatement.setString(4, "" + person.getAdultOrChild().getMarker());
        preparedStatement.setString(5, "" + person.getSex().getSex());
        int rowAffected = preparedStatement.executeUpdate();
        System.out.println("Number of added records: " + rowAffected);
        ResultSet result = preparedStatement.getGeneratedKeys();
        if (result.next()) {
            person.setId(result.getInt(1));
        }
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
        String query = "SELECT ID, NAME, SURNAME, AGE, ADULT_OR_CHILD, SEX " +
                "FROM PERSON";

        List<Person> persons = new ArrayList<>();
        try {
            Statement statement = dbConnection.createStatement();

            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                String name = result.getString(2);
                String surname = result.getString(3);
                int age = result.getInt(4);
                AdultOrChild aoc = AdultOrChild.fromChar(result.getString(5).charAt(0));
                Sex sex = null;
                int id = result.getInt(1);
                Person person = new Person(name, surname, age, aoc, sex);
                person.setId(id);
                persons.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }
}
