package pl.mpas.parent_children.dao;

import pl.mpas.parent_children.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        // save person
        // save children
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
        return null;
    }
}
