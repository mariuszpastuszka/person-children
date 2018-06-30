package pl.mpas.parent_children.dao;

import pl.mpas.parent_children.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
    private Connection dbConnection;

    public PersonDaoImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean savePerson(Person person) {
        if (person.getId() == -1) {
            return false;
        }
        String insertQuery = "INSERT INTO PERSON (NAME, SURNAME, AGE, ADULT_OR_CHILD, SEX) " +
                " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dbConnection.prepareStatement(insertQuery);

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
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
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
}
